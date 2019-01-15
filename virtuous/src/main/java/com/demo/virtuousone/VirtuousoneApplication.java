package com.demo.virtuousone;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = "com.demo.virtuousone.dao")
@EnableScheduling
public class VirtuousoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtuousoneApplication.class, args);
    }

}

