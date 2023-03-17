package com.bilgeadam.service;

import com.bilgeadam.dto.request.GenreIdsRequestDto;
import com.bilgeadam.dto.request.MovieRateRequestDto;
import com.bilgeadam.dto.response.MovieResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.MovieManagerException;
import com.bilgeadam.mapper.IMovieMapper;
import com.bilgeadam.repository.IMovieRepository;
import com.bilgeadam.repository.entity.Genre;
import com.bilgeadam.repository.entity.Movie;
import com.bilgeadam.repository.entity.Recommendation;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService extends ServiceManager<Movie,String> {

    private final IMovieRepository movieRepository;

    private final  GenreService genreService;

    private final RecommendationService recommendationService;

    public MovieService(IMovieRepository movieRepository, GenreService genreService, RecommendationService recommendationService) {
        super(movieRepository);
        this.movieRepository = movieRepository;
        this.genreService = genreService;
        this.recommendationService = recommendationService;
    }

    public Boolean rateMovie(MovieRateRequestDto dto) {
        Optional<Movie> movie=findById(dto.getMovieId());
        if (movie.isEmpty()){
            throw  new MovieManagerException(ErrorType.MOVIE_NOT_FOUND);
        }
        movie.get().getUserRatings().put(dto.getUserId(),dto.getRating());
        update(movie.get());
        return  true;
    }

    public String getByMovieId(String name) {
        Optional<Movie> movie=movieRepository.findOptionalByNameIgnoreCase(name);
        if (movie.isEmpty()){
            throw new MovieManagerException(ErrorType.MOVIE_NOT_FOUND);
        }
        return  movie.get().getId();
    }

    public List<MovieResponseDto> getRecommendation(GenreIdsRequestDto dto) {
        List<Genre> genreList=genreService.getGenresByIds(dto.getGenreIds());
        List<Movie> movies=movieRepository.findAllByGenresIn(genreList);
        Recommendation recommendation=Recommendation.builder().userId(dto.getUserId()).movies(movies).build();
        recommendationService.save(recommendation);

        return movies.stream().map(x->{
            MovieResponseDto movieResponseDto= IMovieMapper.INSTANCE.toMovieResponseDto(x);
            movieResponseDto.setGenresIds(dto.getGenreIds());
            return movieResponseDto;
        }).collect(Collectors.toList());

    }
}
