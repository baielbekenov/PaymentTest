package kg.bbekenov.projecttest.service;

import kg.bbekenov.projecttest.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(User user);
    User create(User user);
    User getByPhone(String phone);
    User getCurrentUser();
}
