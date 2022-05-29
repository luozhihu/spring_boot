package com.example.spring_boot_automatic_assembly.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



//@Configuration配置类会被自动扫描到上下文中
@Configuration
public class HelloWorldConfiguration {
    @Bean
    public int hello() {
        int a = 1 + 1;
        return a;
    }
}