package com.bilgeadam.manager;

import com.bilgeadam.dto.request.UserProfileCreateRequestDto;
import com.bilgeadam.dto.response.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//http://localhost:8099/api/v1/elastic/user
@FeignClient(name = "elastic-user", decode404 = true,url = "http://localhost:8099/api/v1/elastic/user")
public interface ElasticManager {

    @PostMapping("/create")
    public ResponseEntity<UserProfile> create(@RequestBody UserProfileCreateRequestDto dto);

}
