package com.core.service;

import com.core.model.Account;
import com.core.model.Transaction;
import java.util.List;

public interface AccountService {
    Account create(Account account);
    
    List<Account> getAllByPhone(String phoneNumber);
    
    Transaction transfer(Account fromAccount, Account toAccount, double amount);
    
    Account getByNumber(Long accountNumber);
    
    boolean blockAccount(Long id);
}
