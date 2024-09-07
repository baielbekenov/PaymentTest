package kg.angryelizar.paymenttest.service;

import kg.angryelizar.paymenttest.models.Account;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;

@Service
public interface AccountService {
    Account createAccount(Account account);

    String makePayment(Authentication authentication);
}
