package kg.angryelizar.paymenttest.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.angryelizar.paymenttest.dto.JwtAuthenticationResponse;
import kg.angryelizar.paymenttest.dto.SignInDto;
import kg.angryelizar.paymenttest.dto.SignUpDto;
import kg.angryelizar.paymenttest.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "The main controller")
public class MainController {
    private final AuthenticationService authenticationService;

    @GetMapping
    public String index(Authentication authentication) {
        return "Hey!" + authentication.getPrincipal().toString();
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

}
