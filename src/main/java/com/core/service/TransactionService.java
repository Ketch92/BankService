package com.core.service;

import com.core.model.Account;
import com.core.model.Transaction;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    Transaction create(Transaction transaction);
    
    Transaction get(Long id);
    
    List<Transaction> getAllByAccount(Account account,
                                      int page, int pageSize);
    
    List<Transaction> getByAccountAndDateFrom(Account account,
                                              LocalDate dateFrom,
                                              int page, int pageSize);
    
    List<Transaction> getByAccountAndBetweenDates(Account account,
                                                 LocalDate dateFrom,
                                                 LocalDate dateTo,
                                                 int page, int pageSize);
    
    Transaction rollbackTransaction(Long id);
    
    void remove(Long id);
}
