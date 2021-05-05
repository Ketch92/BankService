package com.core.service.mapper.transaction;

import com.core.model.Account;
import com.core.model.Transaction;
import com.core.model.dto.transaction.TransactionRequestDto;
import com.core.model.dto.transaction.TransactionResponseDto;
import com.core.service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionMapperImpl implements TransactionMapper {
    private final ModelMapper modelMapper;
    private final AccountService accountService;
    
    @Override
    public TransactionResponseDto mapToDto(Transaction transaction) {
        TransactionResponseDto dto = modelMapper.map(transaction, TransactionResponseDto.class);
        dto.setFromAccount(transaction.getFromAccount().getAccountNumber().toString());
        dto.setToAccount(transaction.getToAccount().getAccountNumber().toString());
        return dto;
    }
    
    @Override
    public Transaction mapToEntity(TransactionRequestDto dto) {
        Transaction transaction = modelMapper.map(dto, Transaction.class);
        Account fromAccount = accountService.getByNumber(dto.getFromAccount());
        Account toAccount = accountService.getByNumber(dto.getToAccount());
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        return transaction;
    }
}
