package com.bilgeadam.controller;

import com.bilgeadam.dto.request.MovieRateRequestDto;
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

}
