package kg.bbekenov.projecttest.service.impl;

import kg.bbekenov.projecttest.dto.JwtAuthenticationResponse;
import kg.bbekenov.projecttest.dto.SignInDto;
import kg.bbekenov.projecttest.dto.SignUpDto;
import kg.bbekenov.projecttest.models.Account;
import kg.bbekenov.projecttest.models.Transaction;
import kg.bbekenov.projecttest.models.User;
import kg.bbekenov.projecttest.repository.AuthorityRepository;
import kg.angryelizar.paymenttest.service.*;
import kg.bbekenov.projecttest.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthorityRepository authorityRepository;
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final SessionService sessionService;


    @Override
    public JwtAuthenticationResponse signUp(SignUpDto requestDto) {

        User user = User.builder()
                .username(requestDto.getUsername())
                .phone(requestDto.getPhoneNumber())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .authority(authorityRepository.findByAuthority("FULL"))
                .enabled(true)
                .build();
        User registeredUser = userService.create(user);

        Account account = Account.builder()
                .user(registeredUser)
                .balance(BigDecimal.valueOf(0))
                .build();
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(BigDecimal.valueOf(8d))
                .executionTime(LocalDateTime.now())
                .build();
        account.setBalance(transaction.getAmount());
        accountService.createAccount(account);
        transactionService.createTransaction(transaction);
        return authenticateAndSaveSession(registeredUser);
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
        return authenticateAndSaveSession(user);
    }

    private JwtAuthenticationResponse authenticateAndSaveSession(User user) {
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse(jwtService.generateToken(user));
        sessionService.addSession(jwtAuthenticationResponse.getAccessToken(), user);
        return jwtAuthenticationResponse;
    }
}
