package com.bilgeadam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponseDto {
    private String id;
    private List<String> genresIds;
    private  String language;
    private  String image;
    private  String name;
    private  String country;
    private  double rating;
    private  String summary;
    private LocalDate premiered;
}
