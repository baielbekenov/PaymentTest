package kg.angryelizar.paymenttest.service.impl;

import jakarta.transaction.Transactional;
import kg.angryelizar.paymenttest.models.Account;
import kg.angryelizar.paymenttest.models.Transaction;
import kg.angryelizar.paymenttest.models.User;
import kg.angryelizar.paymenttest.repository.AccountRepository;
import kg.angryelizar.paymenttest.repository.UserRepository;
import kg.angryelizar.paymenttest.service.AccountService;
import kg.angryelizar.paymenttest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionService transactionService;

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public String makePayment(Authentication authentication) {
        Optional<User> user = userRepository.findByPhone(authentication.getName());

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        BigDecimal amount = BigDecimal.valueOf(1.1d);
        Account account = accountRepository.findAccountByUser(user.get());
        BigDecimal newBalance = account.getBalance().subtract(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new kg.angryelizar.paymenttest.exceptions.AccountException("Insufficient funds, current balance " + account.getBalance());
        }

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(amount)
                .executionTime(LocalDateTime.now())
                .build();

        transactionService.createTransaction(transaction);
        account.setBalance(newBalance);
        accountRepository.save(account);
        return String.format("Transaction finished - new balance is %s", account.getBalance());
    }
}
