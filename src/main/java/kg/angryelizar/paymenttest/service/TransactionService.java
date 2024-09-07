package kg.angryelizar.paymenttest.service;

import kg.angryelizar.paymenttest.models.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
}
