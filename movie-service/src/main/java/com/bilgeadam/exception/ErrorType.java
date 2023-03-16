package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum ErrorType {
    INTERNAL_ERROR(5300,"Sunucu Hatasi",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4300,"Parametre Hatasi",HttpStatus.BAD_REQUEST),
    MOVIE_NOT_FOUND(4310,"Boyle Bir Film bulunamadi",HttpStatus.BAD_REQUEST),
    MOVIE_NOT_CREATED(4311,"Film olusturulamadi",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4312,"Geçersiz Token",HttpStatus.BAD_REQUEST),

    ;

  private   int code;
  private   String message;
    HttpStatus httpStatus;


}
