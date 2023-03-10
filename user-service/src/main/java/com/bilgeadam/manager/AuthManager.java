package com.bilgeadam.manager;

import com.bilgeadam.dto.request.UpdateByEmailOrUserNameRequestDto;
import com.bilgeadam.dto.response.RoleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.bilgeadam.constant.ApiUrls.UPDATEBYUSERNAMEOREMAIL;

@FeignClient(name = "auth-user", decode404 = true,url = "http://localhost:8090/api/v1/auth")
public interface AuthManager {

    @PutMapping(UPDATEBYUSERNAMEOREMAIL)
    public ResponseEntity<Boolean> updateByUsernameOrEmail(@RequestBody UpdateByEmailOrUserNameRequestDto dto);

    @GetMapping("findbyrole/{role}")
    public  ResponseEntity<List<RoleResponseDto>> findbyRole(@PathVariable String role);

}
