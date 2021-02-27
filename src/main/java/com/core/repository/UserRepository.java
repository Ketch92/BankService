package com.core.repository;

import com.core.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.rolesSet where u.phoneNumber = :number")
    Optional<User> getUserByPhoneNumber(@Param("number") String number);
    
    @Query("select u from User u join fetch u.rolesSet where u.id = :id")
    Optional<User> getById(@Param("id") Long id);
}
