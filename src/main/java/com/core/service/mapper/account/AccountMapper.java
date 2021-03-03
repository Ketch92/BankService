package com.core.service.mapper.account;

import com.core.model.Account;
import com.core.model.dto.account.AccountRequestDto;
import com.core.model.dto.account.AccountResponseDto;
import com.core.service.ToDtoMapper;
import com.core.service.ToEntityMapper;

public interface AccountMapper
        extends ToDtoMapper<AccountResponseDto, Account>,
        ToEntityMapper<Account, AccountRequestDto> {
}
