package com.core.repository;

import com.core.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUserPhoneNumber(String phoneNumber);
    
    Optional<Account> getByAccountNumber(Long accountNumber);
    
    @Modifying
    @Query("update Account a set a.isActive = false where a.accountNumber = ?1")
    void blockAccount(Long accountNumber);
}
