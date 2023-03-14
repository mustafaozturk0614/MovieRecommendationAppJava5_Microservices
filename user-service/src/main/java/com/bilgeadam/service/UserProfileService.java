package com.bilgeadam.service;

import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.request.UserProfileCreateRequestDto;
import com.bilgeadam.dto.response.RoleResponseDto;
import com.bilgeadam.dto.response.UserFindAllResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.UserManagerException;
import com.bilgeadam.manager.AuthManager;
import com.bilgeadam.manager.ElasticManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.IUserProfileRepositroy;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.repository.enums.EStatus;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepositroy userProfileRepositroy;

    private final AuthManager authManager;
    private final ElasticManager elasticManager;
    private final CacheManager cacheManager;

    public UserProfileService(IUserProfileRepositroy userProfileRepositroy, AuthManager authManager, ElasticManager elasticManager, CacheManager cacheManager) {
        super(userProfileRepositroy);
        this.userProfileRepositroy = userProfileRepositroy;
        this.authManager = authManager;
        this.elasticManager = elasticManager;
        this.cacheManager = cacheManager;
    }
    @Transactional
    public Boolean createUser(NewCreateUserRequestDto dto) {
        try {
            UserProfile userProfile= IUserMapper.INSTANCE.toUserProfile(dto);
            save(userProfile);
  //          cacheManager.getCache("myrole").clear();
            UserProfileCreateRequestDto dto1=IUserMapper.INSTANCE.toUserProfileCreateRequestDto(userProfile);
            System.out.println(dto1);
            elasticManager.create(dto1);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            throw  new UserManagerException(ErrorType.USER_NOT_CREATED);
        }

    }

    public Boolean update(UpdateRequestDto dto) {
        Optional<UserProfile> userProfile=userProfileRepositroy.findOptionalByAuthId(dto.getAuthId());
        if (userProfile.isEmpty()){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
        cacheManager.getCache("myusername").evict(userProfile.get().getUsername().toLowerCase());
        if (!dto.getEmail().equals(userProfile.get().getEmail())||!dto.getUsername().equals(userProfile.get().getUsername())){
            userProfile.get().setUsername(dto.getUsername());
           userProfile.get().setEmail(dto.getEmail());
            authManager.updateByUsernameOrEmail(IUserMapper.INSTANCE.toUpdateByEmailOrUserNameRequestDto(dto));
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
}
