package kg.angryelizar.paymenttest.service.impl;

import kg.angryelizar.paymenttest.models.Account;
import kg.angryelizar.paymenttest.repository.AccountRepository;
import kg.angryelizar.paymenttest.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
