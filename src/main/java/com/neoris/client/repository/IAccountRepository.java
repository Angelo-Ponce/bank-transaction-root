package com.neoris.client.repository;

import com.neoris.client.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface IAccountRepository extends JpaRepository<AccountEntity, Long> {

    @Transactional
    @Modifying
    @Query("update account a set a.initialBalance = :initialBalance, a.status = :status where a.accountId = :accountId")
    void updateAccount(@Param("initialBalance") BigDecimal initialBalance,
                       @Param("status") Boolean status,
                       @Param("accountId") Long accountId);
}
