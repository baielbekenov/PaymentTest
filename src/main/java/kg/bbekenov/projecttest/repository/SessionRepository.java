package kg.bbekenov.projecttest.repository;

import kg.bbekenov.projecttest.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAllByUserId(Long userId);
    void deleteByToken(String token);
    Optional<Session> findByToken(String token);
}
