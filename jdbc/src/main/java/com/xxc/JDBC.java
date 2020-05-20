package com.xxc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
  /*  1. 事务：一个包含多个步骤的业务操作。如果这个业务操作被事务管理，则这多个步骤要么同时成功，要么同时失败。
            2. 操作：
            1. 开启事务
            2. 提交事务
            3. 回滚事务
3. 使用Connection对象来管理事务
	* 开启事务：setAutoCommit(boolean autoCommit) ：调用该方法设置参数为false，即开启事务
		* 在执行sql之前开启事务
	* 提交事务：commit()
		* 当所有sql都执行完提交事务
	* 回滚事务：rollback()
		* 在catch中回滚事务
	*/
       //代码实现：
            /*
            * 1、注册驱动
            *2 、 驱动管理器 获取连接
            * 3、执行sql
            * */
  public static void main(String[] args) {
      try {
          Class.forName("com.mysql.jdbc.Driver");//注册驱动
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }
      //
      //3.通过驱动管理器 获取数据库连接对象
      Connection connection=null;
      try {
           connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","root");
          connection.setAutoCommit(false);
          String sql ="insert into user(id,user_name,user_code) values (1,'xxc','xxc')";
          //connection 获取执行sql的对象 statement
         Statement statement= connection.createStatement();
          //执行sql
          boolean i=statement.execute(sql);
          System.out.println(i);


      } catch (Exception e) {
          try {
              connection.rollback();
          } catch (SQLException ex) {
              try {
                  connection.close();
              } catch (SQLException exc) {
                  exc.printStackTrace();
              }
          }

          e.printStackTrace();
      }

  }


}
