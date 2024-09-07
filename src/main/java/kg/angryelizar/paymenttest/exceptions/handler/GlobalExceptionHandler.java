package kg.angryelizar.paymenttest.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    private ErrorResponse handleNoSuchElementException(NoSuchElementException e) {
        return ErrorResponse.builder(e, HttpStatus.NO_CONTENT, e.getMessage()).build();
    }
}
