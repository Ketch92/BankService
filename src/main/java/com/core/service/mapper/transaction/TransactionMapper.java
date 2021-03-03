package com.core.service.mapper.transaction;

import com.core.model.Transaction;
import com.core.model.dto.transaction.TransactionRequestDto;
import com.core.model.dto.transaction.TransactionResponseDto;
import com.core.service.ToDtoMapper;
import com.core.service.ToEntityMapper;

public interface TransactionMapper
        extends ToDtoMapper<TransactionResponseDto, Transaction>,
        ToEntityMapper<Transaction, TransactionRequestDto> {
}
