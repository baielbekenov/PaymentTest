package kg.bbekenov.projecttest.service;

import kg.bbekenov.projecttest.models.Account;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account createAccount(Account account);

    String makePayment(Authentication authentication);
}
