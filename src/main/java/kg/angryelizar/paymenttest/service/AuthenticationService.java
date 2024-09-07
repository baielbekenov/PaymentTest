package kg.angryelizar.paymenttest.service;

import kg.angryelizar.paymenttest.dto.JwtAuthenticationResponse;
import kg.angryelizar.paymenttest.dto.SignInDto;
import kg.angryelizar.paymenttest.dto.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpDto requestDto);
    JwtAuthenticationResponse signIn(SignInDto requestDto);
}
