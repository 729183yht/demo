package cn.itcats.service;

import cn.itcats.domain.Account;

import java.util.List;

public interface AccountService {

    /***
     * 查询所有账户
     * @return
     */
    public List<Account> findAll();


    /***
     * 保存账户
     */
    public void saveAccount(Account account);

}
