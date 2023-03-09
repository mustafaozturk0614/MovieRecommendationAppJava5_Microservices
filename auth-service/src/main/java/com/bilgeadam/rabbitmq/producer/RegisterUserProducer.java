package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.NewCreateUserRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserProducer {
    @Value("${rabbitmq.exchange-auth}")
    private String directExchange;
    @Value("${rabbitmq.registerkey}")
    private String registerBindingKey;
    private final RabbitTemplate rabbitTemplate;

    public void sendNewUser(NewCreateUserRequestModel model){
        rabbitTemplate.convertAndSend(directExchange,registerBindingKey,model);
    }

}
