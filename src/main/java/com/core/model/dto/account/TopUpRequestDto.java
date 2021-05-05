package com.core.model.dto.account;

import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class TopUpRequestDto {
    @Positive
    private double topUpAmount;
}
