package kg.angryelizar.paymenttest.repository;

import kg.angryelizar.paymenttest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
