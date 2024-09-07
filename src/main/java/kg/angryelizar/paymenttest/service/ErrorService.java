package kg.angryelizar.paymenttest.service;

import kg.angryelizar.paymenttest.exceptions.ErrorResponseBody;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ErrorService {
    ErrorResponseBody makeResponse(Exception exception);

    ErrorResponseBody makeResponse(BindingResult exception);
}
