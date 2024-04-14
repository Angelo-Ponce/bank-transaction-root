package com.neoris.vo;

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
public class MovementReportVo {

    private Date movementDate;
    private String name;
    private Integer accountNumber;
    private String accountType;
    private BigDecimal initialBalance;
    private Boolean movementStatus;
    private BigDecimal movementValue;
    private BigDecimal balance;
}
