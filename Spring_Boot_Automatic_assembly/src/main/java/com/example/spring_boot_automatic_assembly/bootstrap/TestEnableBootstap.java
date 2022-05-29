package com.example.spring_boot_automatic_assembly.bootstrap;

import com.example.spring_boot_automatic_assembly.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;



//@Enable模块驱动，实现了按需装配
@EnableHelloWorld
public class TestEnableBootstap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(TestEnableBootstap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        String hello = context.getBean("hello", String.class);
        System.out.println("hello Bean: " + hello);
        context.close();
    }
}