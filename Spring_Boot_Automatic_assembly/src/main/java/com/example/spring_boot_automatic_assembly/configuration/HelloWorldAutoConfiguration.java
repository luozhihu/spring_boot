package com.example.spring_boot_automatic_assembly.configuration;

import com.example.spring_boot_automatic_assembly.annotation.EnableHelloWorld;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableHelloWorld注解是我们前面例子中自定义的模块驱动注解，其引入了hello这个Bean，所以IOC容器中便会存在hello这个Bean了；
//@EnableHelloWorld
//当配置文件中配置了helloworld=true（我们确实添加了这个配置，所以符合要求）则这个类符合扫描规则；
@ConditionalOnProperty(name = "helloworld", havingValue = "true")
public class HelloWorldAutoConfiguration {
    @Bean
    public int hello() {
        int a = 2 + 1;
        return a;
    }
}