package kg.bbekenov.projecttest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.bbekenov.projecttest.dto.JwtAuthenticationResponse;
import kg.bbekenov.projecttest.dto.SignInDto;
import kg.bbekenov.projecttest.dto.SignUpDto;
import kg.bbekenov.projecttest.service.AccountService;
import kg.bbekenov.projecttest.service.AuthenticationService;
import kg.bbekenov.projecttest.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "The main controller")
public class MainController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final JwtService jwtService;

    @Operation(summary = "Remove 1.1 USD from user balance")
    @GetMapping("/payment")
    public String makePayment(Authentication authentication) {
        return accountService.makePayment(authentication);
    }

    @Operation(summary = "Registration")
    @PostMapping("/register")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpDto signUpDto) {
        return authenticationService.signUp(signUpDto);
    }

    @Operation(summary = "Authorization of user")
    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInDto signInDto) {
        return authenticationService.signIn(signInDto);
    }

    @Operation(summary = "Delete access token")
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, Authentication authentication) {
        return jwtService.makeTokenInvalid(request, authentication);
    }

}
