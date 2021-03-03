package com.core.model.dto.account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class AccountRequestDto {
    @Positive
    private long accountNumber;
    @NotBlank
    private String currency;
    @NotBlank
    private String userPhoneNumber;
}
