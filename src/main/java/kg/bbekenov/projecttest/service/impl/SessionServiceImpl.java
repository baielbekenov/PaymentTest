package kg.bbekenov.projecttest.service.impl;

import kg.bbekenov.projecttest.models.Session;
import kg.bbekenov.projecttest.models.User;
import kg.bbekenov.projecttest.repository.SessionRepository;
import kg.bbekenov.projecttest.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;

    @Override
    public Session addSession(String token, User user) {
        return sessionRepository.save(Session.builder()
                .token(token)
                .user(user)
                .build());
    }

    @Override
    public void removeSession(String token) {
        sessionRepository.deleteByToken(token);
    }
}
