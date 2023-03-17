package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecommendationRepository extends MongoRepository<Recommendation,String> {
}
