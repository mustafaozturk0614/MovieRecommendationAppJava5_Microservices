package com.bilgeadam.controller;

import com.bilgeadam.dto.request.GenreIdsRequestDto;
import com.bilgeadam.dto.request.MovieRateRequestDto;
import com.bilgeadam.dto.response.MovieResponseDto;
import com.bilgeadam.repository.entity.Movie;
import com.bilgeadam.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import  static com.bilgeadam.constant.ApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(MOVIE)
public class MovieController {


    private final MovieService movieService;


    @GetMapping(FINDALL)
    public ResponseEntity<List<Movie>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }
    @PostMapping("/ratemovie")
    public ResponseEntity<Boolean> rateMovie(@RequestBody MovieRateRequestDto dto){

        return  ResponseEntity.ok(movieService.rateMovie(dto));
    }

    @PostMapping("/getmovieid/{name}") //localhost:8093/.....movie/getmovieid/Glee
    public ResponseEntity<String> getMovieId(@PathVariable String name){

        return  ResponseEntity.ok(movieService.getByMovieId(name));
    }

    @PostMapping("/getrecommendation")
    public ResponseEntity<List<MovieResponseDto>> getRecommendation(@RequestBody GenreIdsRequestDto dto){

        return  ResponseEntity.ok(movieService.getRecommendation(dto));
    }

}
