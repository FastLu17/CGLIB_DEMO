package com.luxf.aop.demo.controller;

import com.luxf.aop.demo.annotation.AopAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 小66
 * @date 2020-03-06 17:21
 **/
@RestController
public class SubThreeController extends SubOneController {
    /**
     * 继承@Controller和实现接口(可以不是@Controller)、可以获取@RequestMapping的值、
     * 但是 不能出现多个同名的并且RequestMethod相同的完整@RequesMapping路径
     *
     * RequestMethod不同时,请求路径可以相同、可以存着多个 '/test'的路径
     * @param param
     * @return
     */
    @Override
    @AopAnnotation
    @RequestMapping(value = "/test",method = {RequestMethod.POST, RequestMethod.GET})
    public String testXXXX(String param) {
        return "sub3";
    }
}
