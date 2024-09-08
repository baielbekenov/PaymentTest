package kg.angryelizar.paymenttest.service.impl;

import kg.angryelizar.paymenttest.models.Session;
import kg.angryelizar.paymenttest.models.User;
import kg.angryelizar.paymenttest.repository.SessionRepository;
import kg.angryelizar.paymenttest.service.SessionService;
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
