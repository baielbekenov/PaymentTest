package kg.angryelizar.paymenttest.service.impl;

import kg.angryelizar.paymenttest.dto.JwtAuthenticationResponse;
import kg.angryelizar.paymenttest.dto.SignInDto;
import kg.angryelizar.paymenttest.dto.SignUpDto;
import kg.angryelizar.paymenttest.models.Account;
import kg.angryelizar.paymenttest.models.Transaction;
import kg.angryelizar.paymenttest.models.User;
import kg.angryelizar.paymenttest.repository.AuthorityRepository;
import kg.angryelizar.paymenttest.service.*;
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
