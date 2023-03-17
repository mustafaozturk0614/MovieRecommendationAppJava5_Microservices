package com.bilgeadam.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class Movie extends BaseEntity {
    @Id
    private String id;
    private   List<Genre> genres;
    private  String language;
    private  String image;
    private  String name;
    private  String country;
    private  double rating;
    private  String summary;
    private LocalDate premiered;
    private String url;
    private List<MovieComment> comments;
    private Map<Long,Double> userRatings=new HashMap<>();

}
