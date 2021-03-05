package com.core.controller;

import com.core.model.Account;
import com.core.model.dto.account.AccountRequestDto;
import com.core.model.dto.account.AccountResponseDto;
import com.core.model.dto.account.TopUpRequestDto;
import com.core.model.dto.transaction.TransactionRequestDto;
import com.core.model.dto.transaction.TransactionResponseDto;
import com.core.service.AccountService;
import com.core.service.TransactionService;
import com.core.service.mapper.account.AccountMapper;
import com.core.service.mapper.transaction.TransactionMapper;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;
    
    @PostMapping
    public AccountResponseDto createNewAccount(@RequestBody @Valid AccountRequestDto dto) {
        Account account = accountMapper.mapToEntity(dto);
        accountService.createOrUpdate(account);
        return accountMapper.mapToDto(account);
    }
    
    @GetMapping("/by-phone")
    public List<AccountResponseDto> getAllUsersAccounts(@RequestParam("phone") String phoneNumber) {
        return accountService.getAllByPhone(phoneNumber).stream()
                .map(accountMapper::mapToDto)
                .collect(Collectors.toList());
    }
    
    @PostMapping("/transfer")
    public List<TransactionResponseDto> transfer(@RequestBody @Valid TransactionRequestDto dto) {
        return accountService
                .transfer(accountService.getByNumber(dto.getFromAccount()),
                        accountService.getByNumber(dto.getToAccount()),
                        BigDecimal.valueOf(dto.getAmount()))
                .stream()
                .map(transactionMapper::mapToDto)
                .collect(Collectors.toList());
    }
    
    @GetMapping("/{accountNumber}")
    public String getBalance(@PathVariable Long accountNumber) {
        Account byNumber = accountService.getByNumber(accountNumber);
        return byNumber.getBalance().toString().concat(" ").concat(byNumber.getCurrency().name());
    }
    
    @GetMapping("/history/{accountNumber}")
    public List<TransactionResponseDto> getAccountHistory(@PathVariable Long accountNumber,
                                                          @RequestParam(value = "page",
                                                                  defaultValue = "0") int page,
                                                          @RequestParam(value = "size",
                                                                  defaultValue = "10") int size) {
        return transactionService
                .getAllByAccount(accountService.getByNumber(accountNumber), page, size)
                .stream()
                .map(transactionMapper::mapToDto)
                .collect(Collectors.toList());
    }
    
    @PutMapping("/{accountNumber}")
    public String topUpBalance(@PathVariable Long accountNumber,
                               @RequestBody @Valid TopUpRequestDto dto) {
        Account account = accountService.topUpBalance(accountService.getByNumber(accountNumber),
                BigDecimal.valueOf(dto.getTopUpAmount()));
        return account.getBalance().toString().concat(" ").concat(account.getCurrency().name());
    }
    
    @PatchMapping("/{accountNumber}")
    public String blockAccount(@PathVariable Long accountNumber) {
        accountService.blockAccount(accountNumber);
        return "The account was blocked!";
    }
}
