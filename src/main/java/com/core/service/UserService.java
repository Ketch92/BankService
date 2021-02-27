package com.core.service;

import com.core.model.User;

public interface UserService {
    User saveOrUpdate(User user);
    
    User get(Long id);
    
    User getByPhoneNumber(String phoneNumber);
    
    void remove(Long id);
}
