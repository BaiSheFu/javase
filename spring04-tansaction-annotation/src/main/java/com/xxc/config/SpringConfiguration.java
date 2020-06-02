package com.xxc.config;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.xxc")
@EnableTransactionManagement//开启事务
@EnableAspectJAutoProxy// 开启 spring 对注解 AOP 的支持
@Import(JDBCConfiguration.class)
public class SpringConfiguration {

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

  @Bean(name = "transactionManager")
     public PlatformTransactionManager transactionManager(DataSource dataSource){
      DataSourceTransactionManager transactionManager =new DataSourceTransactionManager();
      transactionManager.setDataSource(dataSource);
        return transactionManager;
  }
}
