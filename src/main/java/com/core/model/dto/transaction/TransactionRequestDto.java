package com.core.model.dto.transaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransactionRequestDto {
    @NotBlank
    private long fromAccount;
    @NotBlank
    private long toAccount;
    @Positive
    private String amount;
    @NotBlank
    private String type;
}
