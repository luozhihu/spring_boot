package com.example.springboottesting;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.springboottesting.mapper")
public class SpringBootTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestingApplication.class, args);
    }

}
