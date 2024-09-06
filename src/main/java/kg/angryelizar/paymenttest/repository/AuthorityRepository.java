package kg.angryelizar.paymenttest.repository;

import kg.angryelizar.paymenttest.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
