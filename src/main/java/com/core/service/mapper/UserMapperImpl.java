package com.core.service.mapper;

import com.core.model.User;
import com.core.model.dto.UserRequestDto;
import com.core.model.dto.UserResponseDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserMapperImpl
        implements UserMapper {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ModelMapper mapper;
    
    @Override
    public UserResponseDto mapToDto(User entity) {
        return mapper.map(entity, UserResponseDto.class);
    }
    
    @Override
    public User mapToEntity(UserRequestDto dto) {
        User user = mapper.map(dto, User.class);
        user.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth(), FORMATTER));
        return user;
    }
}
