package com.neoris.core.service;

import com.neoris.client.entity.MovementEntity;
import com.neoris.client.repository.IMovementRepository;
import com.neoris.client.service.IAccountService;
import com.neoris.client.service.IMovementService;
import com.neoris.client.exception.ModelNotFoundException;
import com.neoris.vo.AccountVo;
import com.neoris.vo.MovementReportVo;
import com.neoris.vo.MovementVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MovementService implements IMovementService {

    private final ModelMapper mapper;

    private final IMovementRepository repository;

    private final IAccountService accountService;

    @Override
    public void saveMovement(MovementVo movementVo) throws Exception {
        // Obtener cuenta
        AccountVo accountVo = this.accountService.findById(movementVo.getAccountId());
        if (accountVo == null) {
            log.error("No existe cuenta: " + movementVo.getAccountId());
            throw new ModelNotFoundException("No existe cuenta: " + movementVo.getAccountId());
        }

        if (movementVo.getMovementValue().compareTo(BigDecimal.ZERO) > 0){
            movementVo.setMovementType("DEPOSITO");
        } else if(movementVo.getMovementValue().compareTo(BigDecimal.ZERO) < 0){
            if( accountVo.getInitialBalance().compareTo(BigDecimal.ZERO) == 0 ||
                    accountVo.getInitialBalance().compareTo(movementVo.getMovementValue()) < 0){
                throw new ModelNotFoundException("Saldo no disponible");
            }
            movementVo.setMovementType("RETIRO");
        } else {
            throw new ModelNotFoundException("Movimiento no valido");
        }
        // Actualizar el saldo inicial
        accountVo.setInitialBalance(accountVo.getInitialBalance().add(movementVo.getMovementValue()));
        movementVo.setMovementDate(new Date());
        movementVo.setBalance(accountVo.getInitialBalance());
        movementVo.setStatus(Boolean.TRUE);
        this.save(movementVo);
        this.accountService.save(accountVo);
    }

    @Override
    public MovementVo findById(Long id) {
        Optional<MovementEntity> movementEntity = this.repository.findById(id);
        if (movementEntity.isPresent()) {
            return this.mapper.map(movementEntity, MovementVo.class);
        }
        return null;
    }

    @Override
    public void update(MovementVo movementVo) throws Exception {
        MovementVo movement = this.findById(movementVo.getMovementId());
        if (movement == null) {
            throw new ModelNotFoundException("Movimiento no existe");
        }
        movement.setStatus(movementVo.getStatus());
        save(movementVo);
    }

    @Override
    public void delete(Long id) throws Exception {
        MovementVo movementVo = this.findById(id);
        if (movementVo == null) {
            throw new ModelNotFoundException("Movimiento no existe");
        }
        movementVo.setStatus(Boolean.FALSE);
        this.save(movementVo);
    }

    @Override
    public List<MovementReportVo> reportMovementByDateAndClientId(String clientId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        List<MovementEntity> movementVo = this.repository.reportMovement(clientId, getDateTransform(startDate), getDateTransform(endDate));
        List<MovementReportVo> reportVos = new ArrayList<>();
        if (!movementVo.isEmpty()) {
            movementVo.forEach( data -> {
                MovementReportVo movement = MovementReportVo.builder()
                        .movementDate(data.getMovementDate())
                        .name(data.getAccount().getClient().getName())
                        .accountNumber(data.getAccount().getAccountNumber())
                        .accountType(data.getAccount().getAccountType())
                        .initialBalance(data.getAccount().getInitialBalance())
                        .movementStatus(data.getStatus())
                        .movementValue(data.getMovementValue())
                        .balance(data.getBalance())
                        .build();
                reportVos.add(movement);
            });
        }
        return reportVos;
    }

    private void save(MovementVo movementVo) {
        MovementEntity movementEntity = mapper.map(movementVo, MovementEntity.class);
        repository.save(movementEntity);
        movementVo.setMovementId(movementEntity.getMovementId());
    }

    private Date getDateTransform ( LocalDateTime localDateTime ){
        // Convertir a ZonedDateTime
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());

        // Convertir a Instant
        Instant instant = zonedDateTime.toInstant();

        // Crear Date
        return Date.from(instant);
    }
}