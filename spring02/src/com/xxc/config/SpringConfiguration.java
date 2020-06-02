package com.xxc.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.xxc")
@Import(JDBCConfiguration.class)//导入其他配置类
public class SpringConfiguration {

}
