package com.core.repository;

import com.core.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("from User u join fetch u.roles where u.phoneNumber = :number")
    Optional<User> getUserByPhoneNumber(String number);
    
    @Query("from User u join fetch u.roles where u.id = :id")
    Optional<User> getById(Long id);
}
