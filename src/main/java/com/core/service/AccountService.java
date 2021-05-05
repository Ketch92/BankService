package com.core.service;

import com.core.model.Account;
import com.core.model.Transaction;
import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account createOrUpdate(Account account);
    
    List<Account> getAllByPhone(String phoneNumber);
    
    List<Transaction> transfer(Account fromAccount, Account toAccount, BigDecimal amount);
    
    Account topUpBalance(Account account, BigDecimal amount);
    
    Account getByNumber(Long accountNumber);
    
    void blockAccount(Long accountNumber);
}
