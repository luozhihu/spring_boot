package com.example.spring_boot_automatic_assembly.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@FirstLevelService
public @interface SecondLevelService {
    String value() default "";
}