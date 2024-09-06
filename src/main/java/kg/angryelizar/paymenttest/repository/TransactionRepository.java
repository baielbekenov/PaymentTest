package kg.angryelizar.paymenttest.repository;

import kg.angryelizar.paymenttest.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
