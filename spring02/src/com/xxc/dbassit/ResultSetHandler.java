package com.xxc.dbassit;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {
    /** 结果集封装的方法 */
    Object handle(ResultSet rs);
}
