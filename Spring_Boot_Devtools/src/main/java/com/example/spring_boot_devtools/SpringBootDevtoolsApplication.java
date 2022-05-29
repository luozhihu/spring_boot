package com.example.spring_boot_devtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootDevtoolsApplication {
    @RequestMapping("/")
    String index() {
        return "hello spring";
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDevtoolsApplication.class, args);
    }

}
