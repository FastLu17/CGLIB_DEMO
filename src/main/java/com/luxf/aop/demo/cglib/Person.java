package com.luxf.aop.demo.cglib;

public class Person {

    public String work() {
        System.out.println("工作中..");
        return "ABC";
    }

    public static void main(String[] args) throws Throwable {
        //此处proxy对象是代理类、由于代理类实现了Eat接口、
        Person proxy = CglibProxyHelper.getProxy(Person.class, new Class[]{Eat.class});
        //因此可以强制转换为Eat对象
        Eat eat = (Eat) proxy;
        String work = proxy.work();
        System.out.println("proxy.getClass().getName() = " + proxy.getClass().getName());
        String food = eat.eatFood("food");
        System.out.println("food = " + food);
        System.out.println("work = " + work);
    }
}