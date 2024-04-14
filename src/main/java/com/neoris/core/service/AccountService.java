package com.neoris.core.service;

import com.neoris.client.entity.AccountEntity;
import com.neoris.client.repository.IAccountRepository;
import com.neoris.client.service.IAccountService;
import com.neoris.vo.AccountVo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private final ModelMapper mapper;

    private final IAccountRepository repository;

    @Override
    @Transactional
    public void save(AccountVo accountVo) {
        AccountEntity accountEntity = mapper.map(accountVo, AccountEntity.class);
        this.repository.save(accountEntity);
        accountVo.setAccountId(accountEntity.getAccountId());
    }

    @Override
    @Transactional(readOnly = true)
    public AccountVo findById(Long id) {
        Optional<AccountEntity> accountEntity = this.repository.findById(id);
        if (accountEntity.isPresent()) {
            return this.mapper.map(accountEntity, AccountVo.class);
        }
        return null;
    }

    @Override
    public void update(AccountVo accountVo) {
        if(this.existAccount(accountVo.getAccountId())){
            this.repository.updateAccount(accountVo.getInitialBalance(),
                    accountVo.getStatus(),
                    accountVo.getAccountId());
        }
    }

    @Override
    public void delete(Long id) {
        AccountVo accountVo = this.findById(id);
        accountVo.setStatus(Boolean.FALSE);
        this.save(accountVo);
    }

    private Boolean existAccount(Long id) {
        return this.repository.existsById(id);
    }
}
