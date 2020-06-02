package com.xxc.springtest;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xxc.beans.Account;
import com.xxc.dbassit.BeanHandler;
import com.xxc.dbassit.DBAssit;

import java.beans.PropertyVetoException;

public class SpringTest {
    public static void main(String[] args) throws PropertyVetoException {
        ComboPooledDataSource dataSource =new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");//数据驱动包导入
        dataSource.setJdbcUrl("jdbc:mysql:///springtest");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        DBAssit dbAssit =new DBAssit(dataSource);
        Object object = dbAssit.query("select name ,money from account  where name = ?", new BeanHandler(Account.class),"aaa");
        System.out.println(object);
    }
}
