package kg.bbekenov.projecttest.repository;

import kg.bbekenov.projecttest.models.Account;
import kg.bbekenov.projecttest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByUser(User user);
}
