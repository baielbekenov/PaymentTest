package kg.angryelizar.paymenttest.service;

import kg.angryelizar.paymenttest.models.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account createAccount(Account account);
}
