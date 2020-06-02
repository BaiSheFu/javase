package com.xxc.service.impl;


import com.xxc.factory.BeanFactory;
import com.xxc.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:bean.xml"})
public class AccountServiceImplTest {

    @Autowired
          //  @Qualifier("proxyAccountService")
    IAccountService proxyAccountService;

    @Test
    public void transfer() {
//        ApplicationContext applicationContext =
//                new ClassPathXmlApplicationContext("bean.xml");
//        IAccountService proxyAccountService = (IAccountService) applicationContext.getBean("proxyAccountService");
        proxyAccountService.transfer("aaa","bbb",100f);
    }
}
