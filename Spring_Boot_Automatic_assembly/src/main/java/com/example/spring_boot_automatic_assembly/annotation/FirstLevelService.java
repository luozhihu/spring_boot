package com.example.spring_boot_automatic_assembly.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

//Target设定注解使用范围，通过ElementType来指定注解可使用范围的枚举集合 。
@Target({ElementType.TYPE})
//@Retention与RetentionPolicy这个枚举类型的常量一起指定注释要保留多长时间
@Retention(RetentionPolicy.RUNTIME)
//Documented注解表明这个注释是由 javadoc记录的。 如果一个类型声明被注释了文档化，它的注释成为公共API的一部分。
@Documented
@Service
public @interface FirstLevelService {
    String value() default "";
}
