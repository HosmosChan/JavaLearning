package com.hosmos.reportform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenhuayang
 */
@SpringBootApplication
@MapperScan("com.hosmos.reportform.dao")
public class ReportformApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportformApplication.class, args);
    }
}