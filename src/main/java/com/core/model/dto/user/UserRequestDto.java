package com.core.model.dto.user;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String dateOfBirth;
    @NotBlank
    private String phoneNumber;
}
