package com.bilgeadam.manager;

import com.bilgeadam.dto.request.GenreIdsRequestDto;
import com.bilgeadam.dto.request.MovieRateRequestDto;
import com.bilgeadam.dto.response.MovieResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-movie", decode404 = true,url = "http://localhost:8093/api/v1/movie")
public interface MovieManager {


    @PostMapping("/ratemovie")
    public ResponseEntity<Boolean> rateMovie(@RequestBody MovieRateRequestDto dto);

    @PostMapping("/getmovieid/{name}")
    public ResponseEntity<String> getMovieId(@PathVariable String name);

    @PostMapping("/getrecommendation")
    public ResponseEntity<List<MovieResponseDto>> getRecommendation(@RequestBody GenreIdsRequestDto dto);

}
