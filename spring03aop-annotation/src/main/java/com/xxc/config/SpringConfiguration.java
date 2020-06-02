package com.xxc.config;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.xxc")
@EnableAspectJAutoProxy// 开启 spring 对注解 AOP 的支持
public class SpringConfiguration {

    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner runner(){
        return new QueryRunner();
    }
}
