package com.luxf.aop.demo.cglib;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB的方法拦截器、
 * 实现{@link org.springframework.cglib.proxy.MethodInterceptor}接口的intercept()方法、
 *
 * @author 小66
 * @Description
 * @create 2020-02-21 19:23
 **/
public class CglibProxyHelper implements MethodInterceptor {
    /**
     * @param o 代理类
     * @param method 代理类的方法、-->包含生成代理类的时候实现接口的方法、
     * @param objects 方法的参数、
     * @param methodProxy
     * @return 返回方法的调用结果
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //此处是实现enhancer.setInterfaces(interfaces);中的接口中的方法、
        if ("eatFood".equals(method.getName())) {
            //因为eatFood()不是被代理类(Person)中的方法,所有直接调用methodProxy.invokeSuper(o, objects);会抛出异常、
            //此处单独实现Eat接口中的eatFood()方法、
            return objects[0];
        }
        return methodProxy.invokeSuper(o, objects);
    }

    /**
     * @param clazz      需要被代理的类的Class对象、(被拦截的类的Class对象)
     * @param interfaces 需要实现的接口的Class数组对象
     * @return 指定class的代理类
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> clazz, Class[] interfaces) {
        Enhancer enhancer = new Enhancer();
        //设置需要动态代理的类、 enhancer生成的代理类是这个clazz的子类
        enhancer.setSuperclass(clazz);
        /*方法调用的结果返回固定值、 enhancer.setCallback((FixedValue) () -> "FixedValue");*/
        //设置拦截器
        enhancer.setCallback(new CglibProxyHelper());
        //设置需要实现的接口、-->需要在MethodInterceptor的intercept()方法中单独实现这个接口的方法,否则无法调用接口的方法、
        enhancer.setInterfaces(interfaces);

        /*//此处如果需要使用setCallbackFilter来对不同的方法使用不同的回调、则需要设置多个callback、
        enhancer.setCallbacks(new Callback[]{new CglibProxyHelper(), new CglibProxyHelper()});
        enhancer.setCallbackFilter(method -> {
            //此处的返回值、即callbacks数组的index、
            if ("work".equals(method.getName())) {
                return 0;
            } else {
                return 1;
            }
        });*/

        return (T) enhancer.create();
    }
}
