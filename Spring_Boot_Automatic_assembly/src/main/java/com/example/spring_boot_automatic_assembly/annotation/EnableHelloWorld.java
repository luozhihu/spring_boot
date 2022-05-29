package com.example.spring_boot_automatic_assembly.annotation;

import com.example.spring_boot_automatic_assembly.configuration.HelloWorldConfiguration;
import com.example.spring_boot_automatic_assembly.selector.HelloWorldImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//需要装配的类
@Import(HelloWorldImportSelector.class)
public @interface EnableHelloWorld {
}