package com.luxf.aop.demo.controller;

import com.luxf.aop.demo.annotation.AopAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小66
 * @Description
 * @create 2020-02-21 13:09
 **/
@RestController
public interface AopController {

    @GetMapping("/test")
    @AopAnnotation(value = "test")
    String testXXXX(String param);
}
