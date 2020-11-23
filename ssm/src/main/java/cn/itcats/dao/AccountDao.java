package cn.itcats.dao;

import cn.itcats.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountDao {
    /***
     * 查询所有账户信息
     * @return
     */
    @Select("select * from account")
    public List<Account> findAll();

    /***
     * 添加账户信息
     *
     */
    @Insert("insert into account(name,money) values (#{name},#{money})")
    public void saveAccount(Account account);
}
