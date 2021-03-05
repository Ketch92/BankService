package com.core.service;

import com.core.model.User;
import java.util.Optional;

public interface UserService {
    User saveOrUpdate(User user);
    
    User get(Long id);
    
    Optional<User> getByPhoneNumber(String phoneNumber);
    
    void remove(Long id);
}
