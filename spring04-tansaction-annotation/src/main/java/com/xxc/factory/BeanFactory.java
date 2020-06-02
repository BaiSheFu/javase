package com.xxc.factory;

import com.xxc.service.IAccountService;
import com.xxc.service.impl.AccountServiceImpl;
import com.xxc.utils.TransactionManager;
import sun.rmi.transport.ObjectTable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanFactory {

    IAccountService accountService;
    TransactionManager tx;

    //set方法提供注入
    public void setAccountService(AccountServiceImpl accountService) {
        this.accountService=accountService;
    }

    public void setTxManager(TransactionManager txManager) {
        this.tx=txManager;
    }
   public IAccountService getAccountService(){
        IAccountService proxyAccountService=(IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader()
                , accountService.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)  {
                        Object rtValue =null;
                        tx.beginTransaction();
                        try {
                            rtValue=method.invoke(accountService,args);
                            tx.commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                            tx.rollback();
                        }finally {
                            tx.release();
                        }
                        return rtValue;
                    }
                });
        return proxyAccountService;
    }


}
