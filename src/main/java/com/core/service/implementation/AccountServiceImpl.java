package com.core.service.implementation;

import com.core.lib.DataProcessingException;
import com.core.model.Account;
import com.core.model.Transaction;
import com.core.repository.AccountRepository;
import com.core.repository.TransactionRepository;
import com.core.service.AccountService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    
    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }
    
    @Override
    public List<Account> getAllByPhone(String phoneNumber) {
        return accountRepository.findAllByUserPhoneNumber(phoneNumber);
    }
    
    @Override
    public Transaction transfer(Account fromAccount, Account toAccount, double amount) {
        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setType(Transaction.Type.OUTCOMING);
        transaction.setAmount(BigDecimal.valueOf(amount));
        LocalDateTime stamp = LocalDateTime.now();
        transaction.setTimeStamp(stamp);
        return transactionRepository.save(transaction);
    }
    
    @Override
    public Account getByNumber(Long accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber).orElseThrow(() ->
                new DataProcessingException(String.format("Couldn't find account with number %d",
                        accountNumber)));
    }
    
    @Override
    public boolean blockAccount(Long accountNumber) {
        return accountRepository.blockAccount(accountNumber);
    }
}
