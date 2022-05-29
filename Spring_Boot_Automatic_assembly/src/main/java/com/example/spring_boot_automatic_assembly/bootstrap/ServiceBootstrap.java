package com.example.spring_boot_automatic_assembly.bootstrap;

import com.example.spring_boot_automatic_assembly.service.TestService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


//Component需要扫描才会被扫描到上下文
@ComponentScan("com.example.spring_boot_automatic_assembly.service")
public class ServiceBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);
        TestService testService = context.getBean("testService", TestService.class);
        System.out.println("TestService Bean: " + testService);
        context.close();
    }
}