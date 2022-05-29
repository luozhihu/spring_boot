package com.example.spring_boot_mapper_pagehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
//要让Spring Boot扫描到Mapper接口，需要在Spring Boot入口类中加入@MapperScan("com.example.spring_boot_mapper_pagehelper.mapper")注解。
@MapperScan("com.example.spring_boot_mapper_pagehelper.mapper")
public class SpringBootMapperPageHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMapperPageHelperApplication.class, args);
    }

}
