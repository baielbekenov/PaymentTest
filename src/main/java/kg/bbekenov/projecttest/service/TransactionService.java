package kg.bbekenov.projecttest.service;

import kg.bbekenov.projecttest.models.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
}
