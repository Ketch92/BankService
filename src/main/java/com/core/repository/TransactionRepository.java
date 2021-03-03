package com.core.repository;

import com.core.model.Account;
import com.core.model.Transaction;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("from Transaction t where t.fromAccount = ?1 or t.toAccount =?1")
    List<Transaction> findAllByAccount(Account account, Pageable pageable);
    
    @Query("from Transaction t where t.fromAccount = ?1 or t.toAccount =?1 and t.timeStamp >= ?2")
    List<Transaction> findAllByAccountAndDateFrom(Account account,
                                                  LocalDate dateFrom,
                                                  Pageable pageable);
    
    @Query("from Transaction t where t.fromAccount = ?1 "
             + "or t.toAccount = ?1 and t.timeStamp >= ?2 "
             + "and t.timeStamp <= ?3")
    List<Transaction> findAllByAccountAndBetweenDates(Account account,
                                                      LocalDate dateFrom,
                                                      LocalDate dateTo,
                                                      Pageable pageable);
}
