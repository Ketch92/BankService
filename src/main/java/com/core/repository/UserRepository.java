package com.core.repository;

import com.core.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.roles where u.phoneNumber = ?1")
    Optional<User> getUserByPhoneNumber(String number);
    
    @Query("select u from User u left join fetch u.roles where u.id = ?1")
    Optional<User> findById(Long id);
}
