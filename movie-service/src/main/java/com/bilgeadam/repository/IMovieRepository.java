package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends MongoRepository<Movie,String> {
}
