package com.neoris.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo {

    private Long accountId;;

    private Integer accountNumber;

    private String accountType;

    private BigDecimal initialBalance;

    private Boolean status;

    private Long personId;
}
