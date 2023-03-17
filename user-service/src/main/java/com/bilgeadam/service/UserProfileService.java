package com.bilgeadam.service;

import com.bilgeadam.dto.request.*;
import com.bilgeadam.dto.response.MovieResponseDto;
import com.bilgeadam.dto.response.RoleResponseDto;
import com.bilgeadam.dto.response.UserFindAllResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.manager.AuthManager;
import com.bilgeadam.manager.ElasticManager;
import com.bilgeadam.manager.MovieManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.IUserProfileRepositroy;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.repository.enums.EStatus;
import com.bilgeadam.utility.JwtTokenManager;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepositroy userProfileRepositroy;

    private final AuthManager authManager;
    private final ElasticManager elasticManager;
    private final CacheManager cacheManager;

    private final JwtTokenManager jwtTokenManager;
    private final MovieManager movieManager;

    public UserProfileService(IUserProfileRepositroy userProfileRepositroy, AuthManager authManager, ElasticManager elasticManager, CacheManager cacheManager, JwtTokenManager jwtTokenManager, MovieManager movieManager) {
        super(userProfileRepositroy);
        this.userProfileRepositroy = userProfileRepositroy;
        this.authManager = authManager;
        this.elasticManager = elasticManager;
        this.cacheManager = cacheManager;
        this.jwtTokenManager = jwtTokenManager;
        this.movieManager = movieManager;
    }
    @Transactional
    public Boolean createUser(NewCreateUserRequestDto dto) {
        UserProfile userProfile= IUserMapper.INSTANCE.toUserProfile(dto);
        try {

            save(userProfile);
  //          cacheManager.getCache("myrole").clear();
            elasticManager.create(IUserMapper.INSTANCE.toUserProfileCreateRequestDto(userProfile));
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            throw  new UserManagerException(ErrorType.USER_NOT_CREATED);
        }

    }

    public Boolean update(UpdateRequestDto dto) {
        Optional<Long> authId=jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }

        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        cacheManager.getCache("myusername").evict(userProfile.get().getUsername().toLowerCase());
        if (!dto.getEmail().equals(userProfile.get().getEmail())||!dto.getUsername().equals(userProfile.get().getUsername())){
            userProfile.get().setUsername(dto.getUsername());
           userProfile.get().setEmail(dto.getEmail());
            authManager.updateByUsernameOrEmail("Bearer "+dto.getToken(), UpdateByEmailOrUserNameRequestDto.builder()
                            .email(userProfile.get().getEmail())
                            .username(userProfile.get().getUsername())
                            .id(authId.get())
                    .build());
        }
        userProfile.get().setAbout(dto.getAbout());
        userProfile.get().setAddress(dto.getAddress());
        userProfile.get().setPhone(dto.getPhone());
        userProfile.get().setAvatar(dto.getAvatar());
        update(userProfile.get());
        return true;
    }

    public Boolean activateStatus(Long id) {
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(id);
        if (userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return  true;
    }
    @Cacheable(value = "myusername",key = "#username.toLowerCase()")
    public UserProfile findByUserName(String username) {
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByUsernameEqualsIgnoreCase(username);

        if (userProfile.isPresent()){
            return userProfile.get();
        }else {
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
    }

    @Cacheable(value = "myrole",key = "#role.toLowerCase(Locale.ENGLISH)")
    public List<UserProfile> findByRole(String role) {
        ;
  List<RoleResponseDto> list=authManager.findbyRole(role).getBody();
   List <Optional<UserProfile>> users=list.stream().map(x-> userProfileRepositroy.findOptionalByAuthId(x.getId())).collect(Collectors.toList());
     return   users.stream().map(y->{
          if (y.isPresent()){
            return   y.get();
          }else{
              return  null;
          }
      }  ).collect(Collectors.toList());
    }

    public List<UserFindAllResponseDto> findAllUser() {
       return findAll().stream().map(x->IUserMapper.INSTANCE.toUserFindAllResponseDto(x)).collect(Collectors.toList());

    }

    public Page<UserProfile> findAllPagebale(int pageSize, int pageNumber, String direction, String sortParameter) {
        Sort sort=Sort.by(Sort.Direction.fromString(direction),sortParameter);
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        return userProfileRepositroy.findAll(pageable);
    }

    @Transactional
    public Boolean rateMovie(RateRequestDto dto) {
        Optional<Long> authId=jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().getMyRatings().put(dto.getMovieId(), dto.getRating());
        update(userProfile.get());

        movieManager.rateMovie(
                MovieRateRequestDto.builder()
                        .userId(userProfile.get().getId())
                        .rating(dto.getRating())
                        .movieId(dto.getMovieId())
                .build());

        return true;
    }

    public Boolean addFavMovies(FavMovieRequestDto dto) {
        Optional<Long> authId=jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (!userProfile.get().getFavMovies().contains(dto.getMovieId())){
            userProfile.get().getFavMovies().add(dto.getMovieId());
            update(userProfile.get());
            return true;
        }else{
            throw  new UserManagerException(ErrorType.MOVIE_ALREADY_EXIST);
        }

    }

    public Boolean addFavMoviesByName(FavMovieByNameRequestDto dto) {

        Optional<Long> authId=jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        String id=movieManager.getMovieId(dto.getName()).getBody();
        System.out.println("===>"+id);
        if (!userProfile.get().getFavMovies().contains(id)){
            userProfile.get().getFavMovies().add(id);
            update(userProfile.get());
            return true;
        }else{
            throw  new UserManagerException(ErrorType.MOVIE_ALREADY_EXIST);
        }
    }

    public Boolean addFavGenres(FavGenresRequestDto dto) {
        Optional<Long> authId=jwtTokenManager.getIdFromToken(dto.getToken());
        if (authId.isEmpty()){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (!userProfile.get().getFavGenres().contains(dto.getGenreId())){
            userProfile.get().getFavGenres().add(dto.getGenreId());
            update(userProfile.get());
            return true;
        }else{
            throw  new UserManagerException(ErrorType.GENRE_ALREADY_EXIST);
        }

    }

    public List<MovieResponseDto> getRecommendation(String token) {
        Optional<Long> authId=jwtTokenManager.getIdFromToken(token.substring(7));
        if (authId.isEmpty()){
            throw new UserManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()){
            throw  new UserManagerException(ErrorType.USER_NOT_FOUND);
        }

        // moviecontrollera gidecek olan istek buraya yazılacak

        return movieManager.getRecommendation(GenreIdsRequestDto.builder().genreIds(userProfile.get().getFavGenres())
                        .userId(userProfile.get().getId())
                .build()).getBody();

    }
}
