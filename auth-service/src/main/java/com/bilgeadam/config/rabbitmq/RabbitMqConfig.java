package com.bilgeadam.config.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.exchange-auth}")
    private String exchange;
    @Value("${rabbitmq.registerkey}")
    private String registerKey;
    @Value("${rabbitmq.queueRegister}")
    private String queueNameRegister;

    @Value("${rabbitmq.queueEmail}")
    private  String queueEmail;
    @Value("${rabbitmq.email-key}")
    private  String  emailKey;
      @Bean
      DirectExchange exchangeAuth(){
          return new DirectExchange(exchange);
      }
      @Bean
      Queue registerQueue(){
          return new Queue(queueNameRegister);
      }

    @Bean
    Queue emailQueue(){
        return new Queue(queueEmail);
    }

      @Bean
      public Binding bindingRegister(final Queue registerQueue ,final DirectExchange exchangeAuth ){
          return BindingBuilder.bind(registerQueue).to(exchangeAuth).with(registerKey);
      }



    @Bean
    public Binding bindingMail(final Queue emailQueue ,final DirectExchange exchangeAuth ){
        return BindingBuilder.bind(emailQueue).to(exchangeAuth).with(emailKey);
    }
}
