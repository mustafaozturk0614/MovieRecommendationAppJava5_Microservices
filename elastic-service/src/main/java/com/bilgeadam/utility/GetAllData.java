package com.bilgeadam.utility;

import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllData {

    private final UserProfileService userProfileService;
    private final IUserManager userManager;

//    @PostConstruct
//    public void initData(){
//        List<UserProfile> userProfileList= IUserMapper.INSTANCE.toUserProfiles(userManager.findAll().getBody());
//         userProfileService.saveAll(userProfileList);
//    }

}
