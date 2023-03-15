package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.mapper.IUserMapper;
import com.bilgeadam.rabbitmq.model.NewCreateUserRequestModel;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewUserConsumer {

private final UserProfileService userProfileService;

@RabbitListener(queues = ("${rabbitmq.queueRegister}"))

public  void newUserCreate(NewCreateUserRequestModel model){
    log.info("User {}",model.toString());
    userProfileService.createUser(IUserMapper.INSTANCE.toNewCreateUserRequestDto(model));
}


}
