package com.bilgeadam.service;

import com.bilgeadam.dto.request.MovieRateRequestDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.MovieManagerException;
import com.bilgeadam.repository.IMovieRepository;
import com.bilgeadam.repository.entity.Movie;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService extends ServiceManager<Movie,String> {

    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        super(movieRepository);
        this.movieRepository = movieRepository;
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
}
