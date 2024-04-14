package com.neoris.services.controller;

import com.neoris.client.service.IMovementService;
import com.neoris.vo.BaseResponseVo;
import com.neoris.vo.MovementReportVo;
import com.neoris.vo.MovementVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movimientos")
@RequiredArgsConstructor
public class MovementsController {

    private final IMovementService movementService;

    @PostMapping
    public ResponseEntity<BaseResponseVo> addMovement(@RequestBody MovementVo movementVo) throws Exception {
        this.movementService.saveMovement(movementVo);
        return ResponseEntity.ok(BaseResponseVo.builder().data(movementVo).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseVo> getClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(BaseResponseVo.builder().data(this.movementService.findById(id)).build());
    }

    @PutMapping
    public ResponseEntity<BaseResponseVo> updateStatus(@RequestBody MovementVo movementVo) throws Exception {
        this.movementService.update(movementVo);
        return ResponseEntity.ok(BaseResponseVo.builder().data(movementVo).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseVo> deleteClient(@PathVariable("id") Long id) throws Exception {
        this.movementService.delete(id);
        return ResponseEntity.ok(BaseResponseVo.builder().build());
    }

    @GetMapping("/reportes")
    public ResponseEntity<BaseResponseVo> reportMovementByDateAndClientId(@RequestParam(value = "clientId") String clientId,
                                                                                  @RequestParam(value = "startDate") String startDate,
                                                                                  @RequestParam(value = "endDate") String endDate) throws Exception {
        List<MovementReportVo> movementReportVo = this.movementService.reportMovementByDateAndClientId(clientId, LocalDateTime.parse(startDate), LocalDateTime.parse(endDate));
        return ResponseEntity.ok(BaseResponseVo.builder().data(movementReportVo).build());
    }
}
