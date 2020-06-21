package com.luxf.aop.demo.annotation;

import java.lang.annotation.*;

/**
 * @author 小66
 * @Description
 * @create 2020-02-21 13:21
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AopAnnotation {
    String value() default "value";
}
