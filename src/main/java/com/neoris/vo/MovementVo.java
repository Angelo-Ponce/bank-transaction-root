package com.neoris.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MovementVo {

    private Long movementId;

    @NotNull(message = "{account.id.empty}")
    private Long accountId;

    private Date movementDate;

    private String movementType;

    @NotNull(message = "{movement.value}")
    private BigDecimal movementValue;

    private BigDecimal balance;

    private Boolean status;
}
