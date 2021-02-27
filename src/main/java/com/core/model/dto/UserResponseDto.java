package com.core.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String dateOfBirth;
    private String phoneNumber;
}
