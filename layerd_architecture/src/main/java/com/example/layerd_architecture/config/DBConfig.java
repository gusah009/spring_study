package com.example.layerd_architecture.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class DBConfig implements TransactionManagementConfigurer {
    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8";

    private String username = "root";

    private String password = "akqoadl112#";

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManger();
    }

    @Bean
    public PlatformTransactionManager transactionManger() {
        return new DataSourceTransactionManager(dataSource());
    }
}