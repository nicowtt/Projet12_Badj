package com.eSales.microserviceBusiness.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;



@Configuration
@ComponentScan(basePackages = {"com.eSales"})
@EntityScan("com.eSales")
@EnableJpaRepositories("com.eSales")
@EnableAutoConfiguration
public class TestContextConf {

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(com.mysql.cj.jdbc.Driver.class.getName());
        ds.setUrl("jdbc:mysql://127.0.0.1:9032/badj?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("nicotine");
        return ds;
    }
}
