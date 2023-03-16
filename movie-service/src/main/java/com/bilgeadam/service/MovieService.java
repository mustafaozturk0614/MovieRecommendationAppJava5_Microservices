package com.bilgeadam.service;

import com.bilgeadam.repository.IMovieRepository;
import com.bilgeadam.repository.entity.Movie;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends ServiceManager<Movie,String> {

    private final IMovieRepository movieRepository;

    public MovieService(IMovieRepository movieRepository) {
        super(movieRepository);
        this.movieRepository = movieRepository;
    }
}
