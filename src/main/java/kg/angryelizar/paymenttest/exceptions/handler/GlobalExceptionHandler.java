package kg.angryelizar.paymenttest.exceptions.handler;

import kg.angryelizar.paymenttest.exceptions.AccountException;
import kg.angryelizar.paymenttest.exceptions.ErrorResponseBody;
import kg.angryelizar.paymenttest.exceptions.UserException;
import kg.angryelizar.paymenttest.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorService errorService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    private ErrorResponseBody handleNoSuchElementException(MethodArgumentNotValidException e) {
        return errorService.makeResponse(e.getBindingResult());
    }

    @ExceptionHandler(UserException.class)
    @ResponseBody
    private ErrorResponseBody userException(UserException e) {
        return errorService.makeResponse(e);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    private ErrorResponseBody badCredentialsException() {
        return ErrorResponseBody.builder()
                .title("Bad credentials")
                .reasons(List.of("Login or password incorrect!"))
                .build();
    }

    @ExceptionHandler(AccountException.class)
    @ResponseBody
    private ErrorResponseBody accountException(AccountException e) {
        return errorService.makeResponse(e);
    }
}
