package com.core.model.dto.transaction;

import lombok.Data;

@Data
public class TransactionResponseDto {
    private Long id;
    private String fromAccount;
    private String toAccount;
    private String timeStamp;
    private String amount;
    private String type;
}
