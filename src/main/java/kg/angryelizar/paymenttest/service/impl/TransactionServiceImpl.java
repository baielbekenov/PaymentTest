package kg.angryelizar.paymenttest.service.impl;

import kg.angryelizar.paymenttest.models.Transaction;
import kg.angryelizar.paymenttest.repository.TransactionRepository;
import kg.angryelizar.paymenttest.service.TransactionService;
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
