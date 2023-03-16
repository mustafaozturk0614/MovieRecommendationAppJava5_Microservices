package com.bilgeadam.controller;

import static com.bilgeadam.constant.ApiUrls.*;

import com.bilgeadam.dto.request.NewCreateUserRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.UserFindAllResponseDto;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
    update metodu yazalım
    update ettiğimiz bilgilerde
    email veya username değişir ise auth servicedeki guncelleme metodunu çalıştırsın
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserProfileController {
    private final UserProfileService userProfileService;


    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto){
        return ResponseEntity.ok(userProfileService.createUser(dto));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody @Valid UpdateRequestDto dto){

        return ResponseEntity.ok(userProfileService.update(dto));
    }

    @PostMapping(ACTIVATESTATUS+"/{authId}")
    public ResponseEntity<Boolean> activateStatus(@RequestHeader(value = "Authorization")String token ,@PathVariable  Long authId){
      return   ResponseEntity.ok(userProfileService.activateStatus(authId));
      //  ....... activatestatsus/1
    }
    @PostMapping(ACTIVATESTATUS)
    public ResponseEntity<Boolean> activateStatus2(@RequestParam Long authId){
        return   ResponseEntity.ok(userProfileService.activateStatus(authId));
        // .......activatestatsus?authId=1
    }

    @GetMapping(FINDALL)
    public  ResponseEntity<List<UserFindAllResponseDto>> findAll(){
        return  ResponseEntity.ok(userProfileService.findAllUser());
    }


    @GetMapping(FINDBYUSERNAME)///findbyusername/{username}"
    public  ResponseEntity<UserProfile> findbyUsername(@PathVariable String username){

        return  ResponseEntity.ok(userProfileService.findByUserName(username));
    }

    @GetMapping("/findbyrole/{role}")
    public ResponseEntity<List<UserProfile>> findByRole(@PathVariable  String role){
        return ResponseEntity.ok(userProfileService.findByRole(role));
    }

    @GetMapping("/findallbypageable")
    public ResponseEntity<Page<UserProfile>> findAllPagebale(int pageSize,int pageNumber,@RequestParam(defaultValue = "ASC",required = false) String direction,@RequestParam(defaultValue = "id",required = false) String sortParameter){

        return ResponseEntity.ok(userProfileService.findAllPagebale(pageSize,pageNumber,direction,sortParameter));

    }


}
