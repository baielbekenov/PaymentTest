package kg.bbekenov.projecttest.repository;

import kg.bbekenov.projecttest.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByAuthority(String authority);
}
