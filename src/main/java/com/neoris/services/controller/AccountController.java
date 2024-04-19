package com.neoris.services.controller;

import com.neoris.core.service.AccountService;
import com.neoris.vo.AccountVo;
import com.neoris.vo.BaseResponseVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cuentas")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<BaseResponseVo> addClient(@Valid @RequestBody AccountVo accountVo) {
        this.accountService.save(accountVo);
        return ResponseEntity.ok(BaseResponseVo.builder().data(accountVo).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseVo> getClientById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(BaseResponseVo.builder().data(this.accountService.findById(id)).build());
    }

    @PutMapping
    public ResponseEntity<BaseResponseVo> updateAccoun(@RequestBody AccountVo accountVo) {
        this.accountService.update(accountVo);
        return ResponseEntity.ok(BaseResponseVo.builder().data(accountVo).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseVo> deleteClient(@PathVariable("id") Long id) {
        this.accountService.delete(id);
        return ResponseEntity.ok(BaseResponseVo.builder().build());
    }
}
