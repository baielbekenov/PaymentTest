package kg.bbekenov.projecttest.service.impl;

import kg.bbekenov.projecttest.exceptions.UserException;
import kg.bbekenov.projecttest.models.User;
import kg.bbekenov.projecttest.repository.UserRepository;
import kg.bbekenov.projecttest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User create(User user) {
        if (Boolean.TRUE.equals(userRepository.existsByPhone(user.getPhone()))) {
            throw new UserException("Phone already exists");
        }
        return save(user);
    }

    @Override
    public User getByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException("Phone not found"));
    }

    @Override
    public User getCurrentUser() {
        return getByPhone(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
