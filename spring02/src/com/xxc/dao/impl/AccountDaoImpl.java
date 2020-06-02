package com.xxc.dao.impl;

import com.xxc.beans.Account;
import com.xxc.dao.IAccountDao;
import com.xxc.dbassit.BeanHandler;
import com.xxc.dbassit.BeanListHandler;
import com.xxc.dbassit.DBAssit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("accountDao")
public class AccountDaoImpl implements IAccountDao {
    @Autowired
    private DBAssit dbAssit;


    public void setDbAssit(DBAssit dbAssit) {
        this.dbAssit = dbAssit;
    }

    @Override
    public void save(Account account) {
        dbAssit.update("insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
    }

    @Override
    public void update(Account account) {
        dbAssit.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public void delete(Integer accountId) {
        dbAssit.update("delete from account where id=?",accountId);
    }

    @Override
    public Account findById(Integer accountId) {
        return (Account) dbAssit.query("select * from account where id=?",new BeanHandler<Account>(Account.class),accountId);
    }

    @Override
    public List<Account> findAll() {
        return (List<Account>) dbAssit.query("select * from account where id=?",new  BeanListHandler<Account>(Account.class));
    }
}
