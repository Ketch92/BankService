package com.core.model.dto.transaction;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionResponseDto {
    private Long id;
    private String fromAccount;
    private String toAccount;
    private String timeStamp;
    private BigDecimal amount;
    private String type;
}
