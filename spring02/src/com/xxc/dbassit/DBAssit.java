package com.xxc.dbassit;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBAssit {

    private DataSource dataSource;

    public DBAssit(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** 执行增删改的方法 */
    public int update(String sql,  Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            // 1. 得到连接
            conn = dataSource.getConnection();

            // 2. 使用连接好参数的sql语句创建预处理对象
            pstm = conn.prepareStatement(sql);

            // 3. 得到sql语句参数的源信息（有几个层参数，都什么类型的等）
            ParameterMetaData pmd = pstm.getParameterMetaData();

            // 4. 判断语句中参数的个数和方法参数params的个数是否一致
            int parameterCount = pmd.getParameterCount();
            // 如果需要参数，则开始判断
            if (parameterCount > 0) {
                if(params == null){
                    throw new NullPointerException("没有sql语句执行必须的参数");
                }
                if(params.length != parameterCount) {
                    throw new RuntimeException("参数的参数个数有误！");
                }
            }
            // 5. 给sql语句的参数赋值
            for(int i=0;i<parameterCount;i++){
                pstm.setObject(i+1, params[i]);
            }
            // 6. 执行语句
            int res = pstm.executeUpdate();
            // 7. 返回执行结果
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(conn, pstm, null);
        }
    }

    /** 查询的方法 */
    public Object query(String sql, ResultSetHandler rsh, Object... params) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            // 1. 得到连接
            conn = dataSource.getConnection();

            // 2. 使用连接好参数的sql语句创建预处理对象
            pstm = conn.prepareStatement(sql);

            // 3. 得到sql语句参数的源信息（有几个层参数，都什么类型的等）
            ParameterMetaData pmd = pstm.getParameterMetaData();

            // 4. 判断语句中参数的个数和方法参数params的个数是否一致
            int parameterCount = pmd.getParameterCount();
            // 如果需要参数，则开始判断
            if (parameterCount > 0) {
                if(params == null){
                    throw new NullPointerException("没有sql语句执行必须的参数");
                }
                if(params.length != parameterCount) {
                    throw new RuntimeException("参数的参数个数有误！");
                }
            }
            // 5. 给sql语句的参数赋值
            for(int i=0;i<parameterCount;i++){
                pstm.setObject(i+1, params[i]);
            }
            rs = pstm.executeQuery();

            /** 封装方法需要自己实现 */
            return rsh.handle(rs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            release(conn, pstm, null);
        }

    }

    private void release(Connection conn, PreparedStatement pstm, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}