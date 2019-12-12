package com.eSales.microserviceBusiness.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;



@ComponentScan
@Configuration
public class TestContextConf {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        ds.setUrl("jdbc:mysql://127.0.0.1:9032/badj?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("nicotine");
        return ds;
    }



}
