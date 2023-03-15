package com.bilgeadam.manager;

import com.bilgeadam.dto.response.UserFindAllResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@FeignClient(name = "user-service-elastic-userprofile" ,url = "http://localhost:8091/api/v1/user",decode404 = true)
public interface IUserManager {
    @GetMapping("/findall")
    public  ResponseEntity<List<UserFindAllResponseDto>> findAll();
}
