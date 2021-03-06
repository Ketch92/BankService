package com.core.service.mapper.user;

import com.core.model.User;
import com.core.model.dto.user.UserRequestDto;
import com.core.model.dto.user.UserResponseDto;
import com.core.service.mapper.ToDtoMapper;
import com.core.service.mapper.ToEntityMapper;

public interface UserMapper
        extends ToDtoMapper<UserResponseDto, User>,
        ToEntityMapper<User, UserRequestDto> {
}
