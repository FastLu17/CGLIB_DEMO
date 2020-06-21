package com.luxf.aop.demo.aspect;

import com.luxf.aop.demo.annotation.AopAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 小66
 * @Description
 * @create 2020-02-21 13:10
 **/
@Component
@Aspect
public class DemoAspect {

    @Pointcut("execution(public * com.luxf.*.*(..)) && @annotation(aopAnnotation)")
    public void pointCut(AopAnnotation aopAnnotation) {
    }

    @Around("@annotation(aopAnnotation)")
    public Object doAround(ProceedingJoinPoint pjp, AopAnnotation aopAnnotation) {
        //可以获取注解的相关信息、对注解进行相应的操作
        String value = aopAnnotation.value();
        System.out.println("value = " + value);
        //目标对象的类名、
        String name = pjp.getTarget().getClass().getName();
        //代理对象的类名、-->Spring实现的CGLIB代理
        String thisName = pjp.getThis().getClass().getName();
        //AOP拦截到的方法名、
        Signature signature = pjp.getSignature();
        String signatureName = signature.getName();
        //AopController.methodName(..)
        String shortString = signature.toShortString();
        //public java.lang.String com.luxf.aop.demo.controller.AopController.methodName(java.lang.String)
        String longString = signature.toLongString();
        //AOP拦到的方法参数的值
        Object[] args = pjp.getArgs();
        /*
        *  可以对args参数修改、
        * */
        Object obj = null;
        try {
            // 执行被AOP拦截的方法、 修改了参数,必须将args传进去、否则还是以前的参数
            // obj = pjp.proceed(args);
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            obj = throwable.toString();
        }
        return obj;
    }

    @Around("@annotation(com.luxf.aop.demo.annotation.AopAnnotation)")
    public Object doAround2(ProceedingJoinPoint pjp) {
        Object obj = null;
        try {
            //执行被AOP拦截的方法
            obj = pjp.proceed();
            return obj;
        } catch (Throwable throwable) {
            return null;
        }
    }

    /**
     * 此处不需要catch异常、抛出去即可
     *
     * @param pjp
     * @param aopAnnotation
     * @return
     * @throws Throwable
     */
    @Around("pointCut(aopAnnotation)")
    public Object doAround3(ProceedingJoinPoint pjp, AopAnnotation aopAnnotation) throws Throwable {
        //可以获取注解的相关信息、对注解进行相应的操作
        String value = aopAnnotation.value();
        System.out.println("value = " + value);
        Object obj;
        //执行被AOP拦截的方法
        try {
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            //此处对throwable的信息记录、然后在抛出异常
            throw throwable;
        }
        System.out.println("obj = " + obj);
        return obj;
    }

    @AfterReturning(returning = "response", pointcut = "pointCut(aopAnnotation)")
    public void method(JoinPoint joinPoint, Object response, AopAnnotation aopAnnotation) {
        System.out.println("response = " + response);
        Object[] args = joinPoint.getArgs();
        System.out.println("getSignature = " + joinPoint.getSignature());
        System.out.println("getSourceLocation = " + joinPoint.getSourceLocation());
        System.out.println("toShortString = " + joinPoint.toShortString());
        System.out.println("getStaticPart = " + joinPoint.getStaticPart());
    }

    @AfterThrowing(pointcut = "pointCut(aopAnnotation)", throwing = "e")
    public void afterThrowing(AopAnnotation aopAnnotation, Exception e) {

    }
}
