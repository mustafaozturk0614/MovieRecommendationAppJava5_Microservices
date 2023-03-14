package com.bilgeadam.dto.response;

import com.bilgeadam.repository.entity.BaseEntity;
import com.bilgeadam.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserFindAllResponseDto extends BaseEntity {

    private Long userId;
    private Long authId;
    private String username;
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private String about;
    private EStatus status;



}
