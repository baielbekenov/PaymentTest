package kg.angryelizar.paymenttest.service;

import kg.angryelizar.paymenttest.models.Session;
import kg.angryelizar.paymenttest.models.User;
import org.springframework.stereotype.Service;

@Service
public interface SessionService {
    Session addSession(String token, User user);
    void removeSession(String token);

}
