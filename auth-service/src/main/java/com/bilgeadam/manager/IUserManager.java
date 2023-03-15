package com.bilgeadam.manager;

import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constant.ApiUrls.ACTIVATESTATUS;


@FeignClient(name = "user-userprofile" ,decode404 = true,url = "http://localhost:8091/api/v1/user" )
public interface IUserManager {


    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto);

    @PostMapping(ACTIVATESTATUS+"/{authId}")
    public ResponseEntity<Boolean> activateStatus(@RequestHeader(value = "Authorization")String token,@PathVariable Long authId);
    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus2(@RequestParam Long authId);
}
