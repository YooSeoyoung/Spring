package com.dw.jdbcapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException{ // 쿼리 시도 전에 예외 발생 시
    public InvalidRequestException(){
        super();
    }
    public InvalidRequestException(String message){ // 쿼리를 시도를 했는데 오류가 났을 경우
        super(message);
    }

}
