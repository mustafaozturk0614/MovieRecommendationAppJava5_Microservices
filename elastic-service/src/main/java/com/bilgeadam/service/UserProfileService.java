package com.bilgeadam.service;

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
}
