package com.core.service.mapper;

import com.core.model.User;
import com.core.model.dto.UserRequestDto;
import com.core.model.dto.UserResponseDto;
import com.core.service.ToDtoMapper;
import com.core.service.ToEntityMapper;

public interface UserMapper
        extends ToDtoMapper<UserResponseDto, User>,
        ToEntityMapper<User, UserRequestDto> {
}
