package com.springboot.insurtechbackend.CommonUtils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class SingelJdbcConnect {

    public static JdbcTemplate showSingleTyepValue(){
        DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/insurancedbz?serverTimezone=UTC&characterEncoding=utf-8&useSSL=false");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("123456");

        //创建jdbctemplate对象，设置数据源
        JdbcTemplate jdbcTemplate=new JdbcTemplate(driverManagerDataSource);
        return jdbcTemplate;
    }

}
