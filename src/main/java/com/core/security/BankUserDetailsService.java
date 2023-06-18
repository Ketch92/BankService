package com.core.security;

import com.core.lib.UserAuthenticationException;
import com.core.model.User;
import com.core.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BankUserDetailsService implements UserDetailsService {
    public static final String USER_AUTHENTICATION_EXCEPTION_MESSAGE
            = "Such user isn't registered! Or input data aren't correct!";
    private final UserService userService;
    
    public BankUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    private static UserAuthenticationException get() {
        return new UserAuthenticationException(USER_AUTHENTICATION_EXCEPTION_MESSAGE);
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User user = userService.getByPhoneNumber(phoneNumber)
                .orElseThrow(BankUserDetailsService::get);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getPhoneNumber())
                .password(user.getPassword())
                .roles(user.getRoles().stream()
                        .map(r -> r.getRoleTitle().name())
                        .toArray(String[]::new))
                .build();
    }
}
