package com.bilgeadam.manager;

import com.bilgeadam.dto.request.MovieRateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-movie", decode404 = true,url = "http://localhost:8093/api/v1/movie")
public interface MovieManager {


    @PostMapping("/ratemovie")
    public ResponseEntity<Boolean> rateMovie(@RequestBody MovieRateRequestDto dto);
}
