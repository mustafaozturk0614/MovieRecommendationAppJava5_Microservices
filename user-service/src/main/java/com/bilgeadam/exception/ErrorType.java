package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_ERROR(5200,"Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre Hatasi",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4210,"Boyle Bir kullanici bulunamadi",HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(4211,"Kullanici olusturulamadi",HttpStatus.BAD_REQUEST),
    USERNAME_DUPLICATE(4212,"Böyle bir kullanıcı adı var",HttpStatus.BAD_REQUEST),

    INVALID_TOKEN(4213,"Geçersiz Token",HttpStatus.BAD_REQUEST),
    MOVIE_ALREADY_EXIST(4214,"Film Daha önce favorilere eklenmis",HttpStatus.BAD_REQUEST),
    GENRE_ALREADY_EXIST(4215,"Film Türü daha önce favorilere eklenmis",HttpStatus.BAD_REQUEST);

  private   int code;
  private   String message;
    HttpStatus httpStatus;


}
