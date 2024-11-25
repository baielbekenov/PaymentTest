package kg.bbekenov.projecttest.repository;

import kg.bbekenov.projecttest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);
    Boolean existsByPhone(String phone);
}
