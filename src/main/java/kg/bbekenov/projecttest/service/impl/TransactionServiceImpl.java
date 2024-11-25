package kg.bbekenov.projecttest.service.impl;

import kg.bbekenov.projecttest.models.Transaction;
import kg.bbekenov.projecttest.repository.TransactionRepository;
import kg.bbekenov.projecttest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
