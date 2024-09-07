package kg.angryelizar.paymenttest.service.impl;

import kg.angryelizar.paymenttest.exceptions.UserException;
import kg.angryelizar.paymenttest.models.User;
import kg.angryelizar.paymenttest.repository.UserRepository;
import kg.angryelizar.paymenttest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
