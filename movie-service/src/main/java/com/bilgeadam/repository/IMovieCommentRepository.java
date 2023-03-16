package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Genre;
import com.bilgeadam.repository.entity.MovieComment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieCommentRepository extends MongoRepository<MovieComment,String> {
}
