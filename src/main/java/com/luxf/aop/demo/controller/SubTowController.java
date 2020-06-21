package com.luxf.aop.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小66
 * @date 2020-03-06 17:14
 **/
@RestController
public class SubTowController implements AopController{
    @Override
    @RequestMapping("testXXXX")
    public String testXXXX(String param) {
        return "CBA";
    }
}
