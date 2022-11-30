package org.pegcode.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("org.pegcode.dao.mapper")
@ComponentScan({"org.pegcode.web.controller", "org.pegcode.core.service"})
@SpringBootApplication
public class SpringContextApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringContextApplication.class, args);
    }
}
