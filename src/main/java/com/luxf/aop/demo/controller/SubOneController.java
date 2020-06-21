package com.luxf.aop.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可以继承父类、接口的RequestMapping路径
 * @author 小66
 * @date 2020-03-06 17:13
 **/
@RestController
@RequestMapping("/sub")
public class SubOneController implements AopController {
    /**
     * 可以继承父类、接口的RequestMapping路径
     * @param param
     * @return
     */
    @Override
    public String testXXXX(String param) {
        return "ABC";
    }
}
