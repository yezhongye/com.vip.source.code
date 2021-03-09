package com.study.design.mode.annotation;

import com.study.design.mode.samples.annotation.AnnotationUse;
import com.study.design.mode.samples.annotation.FirstAnnotation;
import org.junit.Test;
import org.junit.platform.commons.util.CollectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 功能描述: <br>
 *
 * @author zjx
 * @version 1.0.0
 * @date 2021/3/8 15:59
 */
public class MyAnnotationTest {

    @Test
    public void myTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        AnnotationUse use = new AnnotationUse();
        Class<?> classObject = use.getClass();


        //反射调用类的公共方法
        Method sayMethod = classObject.getMethod("sayHello");
        System.out.println(sayMethod);
        Object userInfo = classObject.newInstance();
        sayMethod.invoke(userInfo);

        //反射调用类的私有方法
        Method sayMethodPrivate = classObject.getDeclaredMethod("sayHelloPrivate"); //也可以获取非私有方法
        System.out.println(sayMethodPrivate);
        Object userInfoPrivate = classObject.newInstance();
        sayMethodPrivate.setAccessible(true); //获取私有方法需关闭访问权限验证
        sayMethodPrivate.invoke(userInfoPrivate);


        //获取类注解上的参数
        System.out.println("--------------"+use.getUserName()+";"+use.getUserValue());
        FirstAnnotation firstAnnotation = classObject.getAnnotation(FirstAnnotation.class);
        String value = firstAnnotation == null ? "" : firstAnnotation.value();
        String key = firstAnnotation == null ? "" : firstAnnotation.key();
        System.out.println("================"+value+";"+ key);
        if(firstAnnotation != null && firstAnnotation.value().equals("nameValue")){
            use.setUserValue("重新获取年龄");
        }
        System.out.println("--------------"+use.getUserName()+";"+use.getUserValue());
    }

    @Test
    public void myParamTest(){
        String[] obj = getAllParam("1");
        String[] obj2 = getAllParam("1","2");
        String[] obj3 = getAllParam("1","2","3","4","5","6","7");

        System.out.println(Arrays.toString(obj));
        System.out.println(Arrays.toString(obj2));
        System.out.println(Arrays.toString(obj3));
    }

    public String[] getAllParam(String... strings){

        return strings;
    }

}
