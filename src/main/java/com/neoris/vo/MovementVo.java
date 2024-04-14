package com.neoris.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.OnMessage;
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

    private Long accountId;

    private Date movementDate;

    private String movementType;

    private BigDecimal movementValue;

    private BigDecimal balance;

    private Boolean status;
}
