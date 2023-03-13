package com.bilgeadam.utility;

import com.bilgeadam.dto.response.UserFindAllResponseDto;
import com.bilgeadam.manager.IUserManager;
import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllData {

    private final UserProfileService userProfileService;
    private final IUserManager userManager;

//    @PostConstruct
//    public void initData(){
////        List<UserProfile> userProfileList= userManager.findAll().getBody().stream().map(x->IUserMapper.INSTANCE.toUserProfile(x)).collect(Collectors.toList());
////        userProfileService.saveAll(userProfileList);
//   userManager.findAll().getBody().forEach(System.out::println);
//
//    }

}
