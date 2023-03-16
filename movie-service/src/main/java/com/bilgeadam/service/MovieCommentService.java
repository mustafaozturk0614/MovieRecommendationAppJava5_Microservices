package com.bilgeadam.service;

import com.bilgeadam.repository.IMovieCommentRepository;
import com.bilgeadam.repository.IMovieRepository;
import com.bilgeadam.repository.entity.Movie;
import com.bilgeadam.repository.entity.MovieComment;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class MovieCommentService extends ServiceManager<MovieComment,String> {

    private final IMovieCommentRepository movieCommentRepository;

    public MovieCommentService(IMovieCommentRepository movieCommentRepository) {
        super(movieCommentRepository);
        this.movieCommentRepository = movieCommentRepository;
    }
}
