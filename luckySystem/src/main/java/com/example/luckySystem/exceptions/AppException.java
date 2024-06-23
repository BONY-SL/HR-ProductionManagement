package com.example.luckySystem.exceptions;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/* Maintain All Exception this Program Using This AppException Class this Class extend RuntimeException
* */

@Getter
public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


}
