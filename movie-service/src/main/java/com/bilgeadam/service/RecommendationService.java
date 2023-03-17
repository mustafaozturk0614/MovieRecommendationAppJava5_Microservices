package com.bilgeadam.service;

import com.bilgeadam.repository.IRecommendationRepository;
import com.bilgeadam.repository.entity.Recommendation;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService extends ServiceManager<Recommendation,String> {


    private final IRecommendationRepository repository;

    public RecommendationService(IRecommendationRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
