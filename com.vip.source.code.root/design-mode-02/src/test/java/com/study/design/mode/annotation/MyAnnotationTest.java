package com.study.design.mode.annotation;

import com.study.design.mode.samples.annotation.AnnotationUse;
import com.study.design.mode.samples.annotation.FirstAnnotation;
import org.junit.Test;

import java.lang.reflect.Field;
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
    private static final String aa = "1";
    private static final AnnotationUse useA = new AnnotationUse();
    private static final AnnotationUse useB = useA;

    @Test
    public void myTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException, ClassNotFoundException {
        //对象获取
//        AnnotationUse use = new AnnotationUse();
//        Class<?> classObject = use.getClass();
        //类名获取
//        Class<?> classObject = AnnotationUse.class;
        //通过Class静态对象获取
        Class<?> classObject = Class.forName("com.study.design.mode.samples.annotation.AnnotationUse");

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


        Field fieldName =  classObject.getDeclaredField("userName");
        fieldName.setAccessible(true);
        Field fieldValue =  classObject.getDeclaredField("userValue");
        fieldValue.setAccessible(true);
        //获取类注解上的参数
        System.out.println("--------------"+fieldName.get(userInfo)+";"+fieldValue.get(userInfo));
        FirstAnnotation firstAnnotation = classObject.getAnnotation(FirstAnnotation.class);
        String value = firstAnnotation == null ? "" : firstAnnotation.value();
        String key = firstAnnotation == null ? "" : firstAnnotation.key();
        System.out.println("================"+value+";"+ key);
        if(firstAnnotation != null && firstAnnotation.value().equals("nameValue")){
            fieldValue.set(userInfo,"重新获取年龄");
        }
        System.out.println("--------------"+fieldName.get(userInfo)+";"+fieldValue.get(userInfo));
    }

    @Test
    public void myParamTest(){
        String[] obj = getAllParam("1");
        String[] obj2 = getAllParam("1","2");
        String[] obj3 = getAllParam("1","2","3","4","5","6","7");
        String[] thirdInterface = getAllParam("1","2","3","4","5","6","7");

        System.out.println(Arrays.toString(obj));
        System.out.println(Arrays.toString(obj2));
        System.out.println(Arrays.toString(thirdInterface));
    }

    public String[] getAllParam(String... strings){

        return strings;
    }

    @Test
    public void myStringTest(){
//        String a = "1";
//        String b = "1";
//        String c = new String("1");
//        String d = new String("1");
//        System.out.println(a == b);
//        System.out.println(a == c);
//        System.out.println(d == c);
//        System.out.println(a == aa);
        int e = 500;
        int e1 = 500;
        Long f = 200l;
        Double g = 200.00;
        String a = "ab";
        String b = "b";
        String c = "a";
        String d = "a"+"b";
        Integer cc = new Integer(5);
        String dd = "5";
        Integer dd1 = new Integer(dd);




//        System.out.println(a);
//        System.out.println(d);
        System.out.println(cc.equals(dd1));

    }

}
