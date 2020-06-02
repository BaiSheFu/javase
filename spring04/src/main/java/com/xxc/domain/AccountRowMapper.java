package com.xxc.domain;

import com.sun.rowset.internal.Row;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/*
* spring jdbcTemplate 查询结果集
* */
public class AccountRowMapper implements RowMapper {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account =new Account();
        if(resultSet!=null){
            account.setId(resultSet.getInt("id"));
            account.setMoney(resultSet.getFloat("money"));
            account.setName(resultSet.getString("name"));
        }
        return account;
    }
}
