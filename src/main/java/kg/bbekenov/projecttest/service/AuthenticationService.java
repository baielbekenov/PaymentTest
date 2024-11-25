package kg.bbekenov.projecttest.service;

import kg.bbekenov.projecttest.dto.JwtAuthenticationResponse;
import kg.bbekenov.projecttest.dto.SignInDto;
import kg.bbekenov.projecttest.dto.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signUp(SignUpDto requestDto);
    JwtAuthenticationResponse signIn(SignInDto requestDto);
}
