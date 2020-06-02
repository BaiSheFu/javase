package com.xxc.service.impl;

import com.xxc.beans.Account;

import com.xxc.config.SpringConfiguration;
import com.xxc.service.IAccountService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={SpringConfiguration.class})
public class AccountServiceImplTest {
    @Autowired
    IAccountService as;
    @Test
    public void setAccountDao() {
        Account account = new Account();
        account.setName("财小@junit");
        account.setMoney(100000f);
        //ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        //通过注解获取容器
//        ApplicationContext ac =new AnnotationConfigApplicationContext(SpringConfiguration.class);
//        IAccountService as = ac.getBean("accountService",IAccountService.class);
        as.saveAccount(account);
    }


}