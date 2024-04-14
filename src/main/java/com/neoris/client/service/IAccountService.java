package com.neoris.client.service;

import com.neoris.vo.AccountVo;

public interface IAccountService {

    /**
     * Save account.
     *
     * @param accountVo account Object
     */
    void save(AccountVo accountVo);

    /**
     * Find account by id.
     *
     * @param id Account id
     * @return AccountVo account
     */
    AccountVo findById(Long id);

    /**
     * Update account
     * @param accountVo account
     */
    void update(AccountVo accountVo);

    /**
     * Delete account by Id.
     *
     * @param id A account id
     */
    void delete(Long id);
}
