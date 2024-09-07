package kg.angryelizar.paymenttest.service.impl;

import kg.angryelizar.paymenttest.dto.JwtAuthenticationResponse;
import kg.angryelizar.paymenttest.dto.SignInDto;
import kg.angryelizar.paymenttest.dto.SignUpDto;
import kg.angryelizar.paymenttest.models.User;
import kg.angryelizar.paymenttest.repository.AuthorityRepository;
import kg.angryelizar.paymenttest.repository.UserRepository;
import kg.angryelizar.paymenttest.service.AuthenticationService;
import kg.angryelizar.paymenttest.service.JwtService;
import kg.angryelizar.paymenttest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthorityRepository authorityRepository;


    @Override
    public JwtAuthenticationResponse signUp(SignUpDto requestDto) {
        User user = User.builder()
                .username(requestDto.getUsername())
                .phone(requestDto.getPhoneNumber())
                .password(requestDto.getPassword())
                .authority(authorityRepository.findByAuthority("FULL"))
                .build();
        userService.create(user);
        return new JwtAuthenticationResponse(jwtService.generateToken(user));
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getPhoneNumber(),
                        requestDto.getPassword()
                )
        );
        User user = userService.getByPhone(requestDto.getPhoneNumber());
        return new JwtAuthenticationResponse(jwtService.generateToken(user));
    }
}
