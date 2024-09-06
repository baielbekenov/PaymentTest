package kg.angryelizar.paymenttest.repository;

import kg.angryelizar.paymenttest.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
