package com.study.design.mode.samples.proxy;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 功能描述: <br>
 *
 * @author zjx
 * @version 1.0.0
 * @date 2021/2/4 11:07
 */

@Component
@Aspect
public class AnnoAspect {

    @Pointcut("execution(* com.study.design.mode.samples.proxy.App.say(..))")
    public void jointPoint() {
    }

    @Before("jointPoint()")
    public void before() {
        System.out.println("AnnoAspect before say");
    }


    @After("jointPoint()")
    public void after() {
        System.out.println("AnnoAspect after say");
    }
}
