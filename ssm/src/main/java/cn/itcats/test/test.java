package cn.itcats.test;

import cn.itcats.dao.AccountDao;
import cn.itcats.domain.Account;
import cn.itcats.service.AccountService;
import cn.itcats.service.impl.AccountServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class test {

    @Test
    public void run1(){

      ApplicationContext ac=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AccountService accountServiceImpl = (AccountService) ac.getBean("accountservice");
        accountServiceImpl.findAll();

    }
    @Test
    public void run2() throws IOException {
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = build.openSession();
        AccountDao mapper = session.getMapper(AccountDao.class);
        List<Account> all = mapper.findAll();
        for (Account account : all) {
            System.out.println(account);
        }

        System.out.println(all);

        session.close();
        in.close();
    }

    /***
     * 保存测试
     * @throws IOException
     */
    @Test
    public void run3() throws IOException {
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(in);
        SqlSession session = build.openSession();
        AccountDao mapper = session.getMapper(AccountDao.class);
        Account account=new Account();
        account.setName("yuhuitao");
        account.setMoney(123);
        mapper.saveAccount(account);
        session.commit();
        session.close();
        in.close();
    }
}
