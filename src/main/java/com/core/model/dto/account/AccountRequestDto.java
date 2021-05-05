package com.core.model.dto.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class AccountRequestDto {
    @Positive
    private long accountNumber;
    @NotBlank
    @NotNull
    private String currency;
    @Positive
    private long userId;
}
