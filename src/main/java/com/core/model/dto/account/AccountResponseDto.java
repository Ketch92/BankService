package com.core.model.dto.account;

import lombok.Data;

@Data
public class AccountResponseDto {
    private String accountNumber;
    private String currency;
    private String balance;
    private String userPhoneNumber;
}
