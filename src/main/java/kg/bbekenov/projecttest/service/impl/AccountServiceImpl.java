package kg.bbekenov.projecttest.service.impl;

import jakarta.transaction.Transactional;
import kg.bbekenov.projecttest.models.Account;
import kg.bbekenov.projecttest.models.Transaction;
import kg.bbekenov.projecttest.models.User;
import kg.bbekenov.projecttest.repository.AccountRepository;
import kg.bbekenov.projecttest.repository.UserRepository;
import kg.bbekenov.projecttest.service.AccountService;
import kg.bbekenov.projecttest.service.TransactionService;
import kg.bbekenov.projecttest.exceptions.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
            throw new AccountException("Insufficient funds, current balance " + account.getBalance());
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
