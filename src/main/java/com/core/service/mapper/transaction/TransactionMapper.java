package com.core.service.mapper.transaction;

import com.core.model.Transaction;
import com.core.model.dto.transaction.TransactionRequestDto;
import com.core.model.dto.transaction.TransactionResponseDto;
import com.core.service.mapper.ToDtoMapper;
import com.core.service.mapper.ToEntityMapper;

public interface TransactionMapper
        extends ToDtoMapper<TransactionResponseDto, Transaction>,
        ToEntityMapper<Transaction, TransactionRequestDto> {
}
