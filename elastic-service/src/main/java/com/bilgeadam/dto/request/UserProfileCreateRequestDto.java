package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileCreateRequestDto {

    private Long id;
    private Long authId;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private String about;



}
