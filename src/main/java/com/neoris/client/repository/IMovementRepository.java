package com.neoris.client.repository;

import com.neoris.client.entity.MovementEntity;
import com.neoris.vo.MovementVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IMovementRepository extends JpaRepository<MovementEntity, Long> {

    @Query("select m FROM movement m where m.account.client.clientId = :clientId and m.movementDate between :startDate and :endDate")
    List<MovementEntity> reportMovement(@Param("clientId") String clientId,
                                      @Param("startDate") Date startDate,
                                      @Param("endDate") Date endDate);
}
