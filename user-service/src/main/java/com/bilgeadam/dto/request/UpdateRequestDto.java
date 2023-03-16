package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestDto {
    private String token;
    @NotBlank
    @Size(min=3,max=20)
    private String username;
    @Email
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private String about;
}
