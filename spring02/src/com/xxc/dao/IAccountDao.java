package com.xxc.dao;

import com.xxc.beans.Account;
import com.xxc.dbassit.BeanHandler;
import com.xxc.dbassit.BeanListHandler;
import com.xxc.dbassit.DBAssit;
import sun.nio.ch.Net;

import java.util.List;

public interface IAccountDao {

        /**
         * 保存
         * @param account
         */
        void save(Account account);

        /**
         * 更新
         * @param account
         */
        void update(Account account);

        /**
         * 删除
         * @param accountId
         */
        void delete(Integer accountId);

        /**
         * 根据 id 查询
         * @param accountId
         * @return
         */
        Account findById(Integer accountId);

        /**
         * 查询所有
         * @return
         */
        List<Account> findAll();

    }


