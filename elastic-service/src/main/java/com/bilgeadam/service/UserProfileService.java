package com.bilgeadam.service;

import com.bilgeadam.dto.request.UserProfileCreateRequestDto;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.IUserProfileRepositroy;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepositroy userProfileRepositroy;
    public UserProfileService(IUserProfileRepositroy userProfileRepositroy) {
        super(userProfileRepositroy);
        this.userProfileRepositroy = userProfileRepositroy;
    }


    public UserProfile create(UserProfileCreateRequestDto dto) {

        try {
            UserProfile userProfile=IUserMapper.INSTANCE.toUserProfile(dto);
            save(userProfile);
            System.out.println(userProfile);
            return userProfile;
        }catch (Exception e){
            e.printStackTrace();
                throw new RuntimeException();
        }

    }
}
