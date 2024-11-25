package kg.bbekenov.projecttest.service;

import kg.bbekenov.projecttest.models.Session;
import kg.bbekenov.projecttest.models.User;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    Session addSession(String token, User user);
    void removeSession(String token);

}
