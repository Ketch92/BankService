package com.core.service.implementation;

import com.core.lib.DataProcessingException;
import com.core.model.User;
import com.core.repository.UserRepository;
import com.core.service.RoleService;
import com.core.service.UserService;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final RoleService roleService;
    
    @Override
    public User saveOrUpdate(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    @PostConstruct
    private void injectAdmin() {
        User admin = new User();
        admin.setName("Admin");
        admin.setDateOfBirth(LocalDate.of(2000, 1, 1));
        admin.setPassword(encoder.encode("1234"));
        admin.setPhoneNumber("0000");
        admin.setRoles(Set.of(roleService.getByName("ADMIN")));
        userRepository.save(admin);
    }
    
    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataProcessingException(String.format("User with id=%d wasn't found.", id)));
    }
    
    @Override
    public Optional<User> getByPhoneNumber(String phoneNumber) {
        return userRepository.getUserByPhoneNumber(phoneNumber);
    }
    
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
