package com.study.design.mode.samples.annotation;

/**
 * 功能描述: <br>
 *
 * @author zjx
 * @version 1.0.0
 * @date 2021/2/4 13:54
 */
@FirstAnnotation(key = "nameKey",value = "nameValue")
public class AnnotationUse {

    public AnnotationUse(){
        this.userName = "mike";
        this.userValue = "35age";
    }

    public void sayHello(){
        System.out.println("请大声说一声hello");
    }
    private void sayHelloPrivate(){
        System.out.println("请私有的说一声hello");
    }

    private String userName;
    private String userValue;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserValue() {
        return userValue;
    }

    public void setUserValue(String userValue) {
        this.userValue = userValue;
    }
}
