package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.UserProfileCreateRequestDto;
import com.bilgeadam.dto.response.UserFindAllResponseDto;
import com.bilgeadam.repository.entity.UserProfile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-15T09:32:05+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.1.jar, environment: Java 17.0.5 (JetBrains s.r.o.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public List<UserProfile> toUserProfiles(List<UserFindAllResponseDto> dto) {
        if ( dto == null ) {
            return null;
        }

        List<UserProfile> list = new ArrayList<UserProfile>( dto.size() );
        for ( UserFindAllResponseDto userFindAllResponseDto : dto ) {
            list.add( toUserProfile( userFindAllResponseDto ) );
        }

        return list;
    }

    @Override
    public UserProfile toUserProfile(UserFindAllResponseDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.createdate( dto.getCreatedate() );
        userProfile.updatedate( dto.getUpdatedate() );
        userProfile.userId( dto.getUserId() );
        userProfile.authId( dto.getAuthId() );
        userProfile.username( dto.getUsername() );
        userProfile.email( dto.getEmail() );
        userProfile.phone( dto.getPhone() );
        userProfile.avatar( dto.getAvatar() );
        userProfile.address( dto.getAddress() );
        userProfile.about( dto.getAbout() );
        userProfile.status( dto.getStatus() );

        return userProfile.build();
    }

    @Override
    public UserProfile toUserProfile(UserProfileCreateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder<?, ?> userProfile = UserProfile.builder();

        userProfile.createdate( dto.getCreatedate() );
        userProfile.updatedate( dto.getUpdatedate() );
        userProfile.userId( dto.getUserId() );
        userProfile.authId( dto.getAuthId() );
        userProfile.username( dto.getUsername() );
        userProfile.email( dto.getEmail() );
        userProfile.phone( dto.getPhone() );
        userProfile.avatar( dto.getAvatar() );
        userProfile.address( dto.getAddress() );
        userProfile.about( dto.getAbout() );

        return userProfile.build();
    }
}
