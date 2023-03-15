package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.UserProfileCreateRequestDto;
import com.bilgeadam.dto.response.UserFindAllResponseDto;
import com.bilgeadam.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {


    IUserMapper INSTANCE= Mappers.getMapper(IUserMapper.class);

    List<UserProfile> toUserProfiles(final List<UserFindAllResponseDto> dto);
    UserProfile toUserProfile(final UserFindAllResponseDto dto);


    UserProfile toUserProfile(final UserProfileCreateRequestDto dto);

}
