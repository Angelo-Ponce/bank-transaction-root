package com.neoris.client.service;

import com.neoris.vo.MovementReportVo;
import com.neoris.vo.MovementVo;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IMovementService {
    /**
     * Save movement.
     *
     * @param movementVo movement Object
     */
    void saveMovement(MovementVo movementVo) throws Exception;

    /**
     * Find movement by id.
     *
     * @param id Movement id
     * @return MovementVo movement
     */
    MovementVo findById(Long id);

    /**
     * Update movement
     *
     * @param movementVo movement
     */
    void update(MovementVo movementVo) throws Exception;

    /**
     * Delete movement by Id.
     *
     * @param id A movement id
     */
    void delete(Long id) throws Exception;

    /**
     * Report movement by date and client id
     * @param clientId client id
     * @param startDate start date
     * @param endDate end date
     * @return
     * @throws Exception
     */
    List<MovementReportVo> reportMovementByDateAndClientId(String clientId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
}