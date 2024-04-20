package com.neoris.services.controller;

import com.neoris.core.service.AccountService;
import com.neoris.vo.AccountVo;
import com.neoris.vo.BaseResponseVo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cuentas")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @PreAuthorize("hasRole('admin_client_role') or hasRole('user_client_role')")
    public ResponseEntity<BaseResponseVo> addAccount(@Valid @RequestBody AccountVo accountVo) {
        this.accountService.save(accountVo);
        return ResponseEntity.ok(BaseResponseVo.builder().data(accountVo).build());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin_client_role') or hasRole('user_client_role')")
    public ResponseEntity<BaseResponseVo> getAccountById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(BaseResponseVo.builder().data(this.accountService.findById(id)).build());
    }

    @PutMapping
    @PreAuthorize("hasRole('admin_client_role') or hasRole('user_client_role')")
    public ResponseEntity<BaseResponseVo> updateAccount(@RequestBody AccountVo accountVo) {
        this.accountService.update(accountVo);
        return ResponseEntity.ok(BaseResponseVo.builder().data(accountVo).build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin_client_role')")
    public ResponseEntity<BaseResponseVo> deleteAccount(@PathVariable("id") Long id) {
        this.accountService.delete(id);
        return ResponseEntity.ok(BaseResponseVo.builder().build());
    }
}
