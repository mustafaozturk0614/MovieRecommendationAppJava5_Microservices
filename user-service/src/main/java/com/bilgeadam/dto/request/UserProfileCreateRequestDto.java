package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
