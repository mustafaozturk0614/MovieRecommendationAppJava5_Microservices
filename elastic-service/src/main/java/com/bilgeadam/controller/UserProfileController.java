package com.bilgeadam.controller;

import static com.bilgeadam.constant.ApiUrls.* ;
import com.bilgeadam.dto.request.UserProfileCreateRequestDto;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {

    private  final UserProfileService userProfileService;

    private final IUserManager userManager;

    @GetMapping("/findall")
    public ResponseEntity<Iterable<UserProfile>> findAll(){
//            userManager.findAll().getBody().forEach(System.out::println);


        return  ResponseEntity.ok(userProfileService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<UserProfile> create(@RequestBody UserProfileCreateRequestDto dto){
        System.out.println(dto);
        return  ResponseEntity.ok(userProfileService.create(dto));
    }
}
