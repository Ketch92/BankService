package com.core.service.mapper.account;

import com.core.model.Account;
import com.core.model.Currency;
import com.core.model.dto.account.AccountRequestDto;
import com.core.model.dto.account.AccountResponseDto;
import com.core.service.UserService;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountMapperImpl implements AccountMapper {
    private final ModelMapper modelMapper;
    private final UserService userService;
    
    @Override
    public AccountResponseDto mapToDto(Account account) {
        return modelMapper.map(account, AccountResponseDto.class);
    }
    
    @Override
    public Account mapToEntity(AccountRequestDto dto) {
        Account account = modelMapper.map(dto, Account.class);
        account.setCurrency(Currency.valueOf(dto.getCurrency().toUpperCase()));
        account.setBalance(BigDecimal.ZERO);
        account.setUser(userService.get(dto.getUserId()));
        account.setActive(true);
        return account;
    }
}
