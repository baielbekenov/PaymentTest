package kg.angryelizar.paymenttest.repository;

import kg.angryelizar.paymenttest.models.Account;
import kg.angryelizar.paymenttest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUser(User user);
}
