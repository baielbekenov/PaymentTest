package kg.bbekenov.projecttest.repository;

import kg.bbekenov.projecttest.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
