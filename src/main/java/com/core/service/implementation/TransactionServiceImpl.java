package com.core.service.implementation;

import com.core.lib.DataProcessingException;
import com.core.model.Account;
import com.core.model.Transaction;
import com.core.repository.TransactionRepository;
import com.core.service.TransactionService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    
    @Override
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    @Override
    public Transaction get(Long id) {
        return transactionRepository.findById(id).orElseThrow(() ->
                new DataProcessingException(String.format("Transaction with id=%d wasn't found",
                        id)));
    }
    
    @Override
    public List<Transaction> getAllByAccount(Account account,
                                             int page, int pageSize) {
        return transactionRepository.findAllByAccount(account,
                PageRequest.of(page, pageSize, Sort.by("timeStamp").descending()));
    }
    
    @Override
    public List<Transaction> getByAccountAndDateFrom(Account account,
                                                     LocalDate dateFrom,
                                                     int page, int pageSize) {
        return transactionRepository.findAllByAccountAndDateFrom(account, dateFrom,
                PageRequest.of(page, pageSize, Sort.by("timeStamp").descending()));
    }
    
    @Override
    public List<Transaction> getByAccountAndBetweenDates(Account account,
                                                        LocalDate dateFrom,
                                                        LocalDate dateTo,
                                                        int page, int pageSize) {
        return transactionRepository.findAllByAccountAndBetweenDates(account, dateFrom,
                dateTo, PageRequest.of(page, pageSize, Sort.by("timeStamp").descending()));
    }
    
    @Override
    public Transaction rollbackTransaction(Long id) {
        Optional<Transaction> toRollback = transactionRepository.findById(id);
        if (toRollback.isPresent()) {
            Transaction transaction = toRollback.get();
            Account rollbackToAccount = transaction.getFromAccount();
            Account rollbackFromAccount = transaction.getToAccount();
            transaction.setId(null);
            transaction.setToAccount(rollbackToAccount);
            transaction.setFromAccount(rollbackFromAccount);
            return transactionRepository.save(transaction);
        }
        throw new DataProcessingException(String.format("Transaction with id=%d wasn't found", id));
    }
    
    @Override
    public void remove(Long id) {
        transactionRepository.deleteById(id);
    }
}
