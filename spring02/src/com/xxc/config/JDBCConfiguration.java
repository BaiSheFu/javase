package com.xxc.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xxc.dbassit.DBAssit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
@Configuration
@PropertySource("classpath:jdbc.properties")//加载配置文件
public class JDBCConfiguration {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Bean(name = "dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setUser(username);
            ds.setPassword(password);
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 创建一个 DBAssit，并且也存入 spring 容器中
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "dbAssit")
    public DBAssit createDBAssit(DataSource dataSource) {
        return new DBAssit(dataSource);
    }
}
