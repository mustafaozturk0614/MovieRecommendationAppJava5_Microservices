package com.bilgeadam;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootApplication
public class MailServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class);
    }


// Deneme amaçlı kod
//    private final JavaMailSender javaMailSender;
//
//    public MailServiceApplication(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//    @Value("${spring.mail.username}")
//    private String mymail;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        SimpleMailMessage mailMessage=new SimpleMailMessage();
//        mailMessage.setFrom("${mailusername}");
//        mailMessage.setTo("musty1406@gmail.com");
//        mailMessage.setSubject("Aktivasyon kodunuz: ");
//        mailMessage.setText("123548");
//        javaMailSender.send(mailMessage);
//    }


}