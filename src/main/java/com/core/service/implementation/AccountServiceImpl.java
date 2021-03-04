package com.core.service.implementation;

import com.core.lib.DataProcessingException;
import com.core.lib.IllegalTransferException;
import com.core.model.Account;
import com.core.model.Transaction;
import com.core.repository.AccountRepository;
import com.core.repository.TransactionRepository;
import com.core.service.AccountService;
import com.core.service.currency.CurrencyConversionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    public static final String TRANSFER_EXCEPTION_MESSAGE = "Wrong transfer amount of money.";
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final CurrencyConversionService currencyService;
    
    @Transactional
    @Override
    public Account createOrUpdate(Account account) {
        return accountRepository.save(account);
    }
    
    @Override
    public List<Account> getAllByPhone(String phoneNumber) {
        return accountRepository.findAllByUserPhoneNumber(phoneNumber);
    }
    
    @Transactional
    @Override
    public List<Transaction> transfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        if (fromAccount.getBalance().compareTo(amount) >= 0) {
            throw new IllegalTransferException(TRANSFER_EXCEPTION_MESSAGE);
        }
        BigDecimal convertedAmount = BigDecimal.valueOf(currencyService.convert(fromAccount.getCurrency(),
                toAccount.getCurrency(), amount.doubleValue()));
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(convertedAmount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
        return transactionRepository.saveAll(getTransactions(fromAccount,
                toAccount, amount, convertedAmount, LocalDateTime.now()));
    }
    
    @Transactional
    @Override
    public Account topUpBalance(Account account, BigDecimal amount) {
        Transaction topUp = new Transaction();
        topUp.setTimeStamp(LocalDateTime.now());
        topUp.setToAccount(account);
        topUp.setFromAccount(account);
        topUp.setAmount(amount);
        topUp.setType(Transaction.Type.TOP_UP);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        transactionRepository.save(topUp);
        return account;
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
    
    private List<Transaction> getTransactions(Account from, Account to,
                                              BigDecimal amount, BigDecimal convertedAmount,
                                              LocalDateTime stamp) {
        Transaction outcoming = new Transaction();
        outcoming.setFromAccount(from);
        outcoming.setToAccount(to);
        outcoming.setType(Transaction.Type.OUT_COMING);
        outcoming.setAmount(amount);
        outcoming.setTimeStamp(stamp);
        
        Transaction incoming = new Transaction();
        incoming.setFromAccount(from);
        incoming.setToAccount(to);
        incoming.setType(Transaction.Type.IN_COMING);
        incoming.setTimeStamp(stamp);
        incoming.setAmount(convertedAmount);
        return List.of(incoming, outcoming);
    }
}
