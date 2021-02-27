package com.core.service.implementation;

import com.core.lib.DataProcessingException;
import com.core.model.User;
import com.core.repository.UserRepository;
import com.core.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    
    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new DataProcessingException(String.format("User with id=%d wasn't found.", id)));
    }
    
    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.getUserByPhoneNumber(phoneNumber).orElseThrow(() ->
                new DataProcessingException(String.format("User with %s phone "
                                                          + "number wasn't found.", phoneNumber)));
    }
    
    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }
}
