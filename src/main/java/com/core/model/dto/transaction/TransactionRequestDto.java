package com.core.model.dto.transaction;

import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDto {
    @Positive
    private long fromAccount;
    @Positive
    private long toAccount;
    @Positive
    private double amount;
}
