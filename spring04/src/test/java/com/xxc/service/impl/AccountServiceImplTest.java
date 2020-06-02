package com.xxc.service.impl;

import com.xxc.domain.Account;
import com.xxc.domain.AccountRowMapper;
import com.xxc.factory.BeanFactory;
import com.xxc.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:bean.xml"})
public class AccountServiceImplTest {

    /*//    @Autowired
              //  @Qualifier("proxyAccountService")
        IAccountService proxyAccountService;

        @Test
        public void transfer() {
    //        ApplicationContext applicationContext =
    //                new ClassPathXmlApplicationContext("bean.xml");
    //        IAccountService proxyAccountService = (IAccountService) applicationContext.getBean("proxyAccountService");
            proxyAccountService.transfer("aaa","bbb",100f);
        }*/
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    public void testJdbcTemplate() {
          //  jdbcTemplate.execute("insert into account(name,money)values('eee',500)");
            //jdbcTemplate.update("update account set money=? where name =? ",5000,"eee" );
            List<Account> accounts=jdbcTemplate.query("select *from account",new AccountRowMapper());
           System.out.println(accounts);
    }
    @Autowired
    IAccountService accountService;
    @Test
    public void testQuery(){
       Account account= accountService.findAccountById(1);
        System.out.println(account);
    }
    @Test
    public void testTransfer(){
        accountService.transfer("aaa","bbb",100f);
    }
}
