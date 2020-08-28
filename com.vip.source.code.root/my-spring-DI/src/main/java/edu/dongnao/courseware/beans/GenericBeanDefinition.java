package edu.dongnao.courseware.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * BeanDefinition接口的实现
 */
public class GenericBeanDefinition implements BeanDefinition {
    private Class<?> beanClass;
    private String factoryMethodName;
    private Method factoryMethod;
    private String factoryBeanName;
    private String initMethod;
    private String destroyMethod;
    private List<?> constructorArgumentValues;
    private List<PropertyValue> propertyValues;
    private Constructor constructor;
    private String scope = BeanDefinition.SCOPE_SINGLETON;
    
    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean isSingleton() {
        return BeanDefinition.SCOPE_SINGLETON.equals(scope);
    }

    @Override
    public boolean isPrototype() {
        return BeanDefinition.SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public String getFactoryMethodName() {
        return factoryMethodName;
    }

    @Override
    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }

    @Override
    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    @Override
    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    @Override
    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    @Override
    public String getInitMethod() {
        return initMethod;
    }

    @Override
    public void setDestroyMethod(String destroyMethod) {
        this.destroyMethod = destroyMethod;
    }

    @Override
    public String getDestroyMethod() {
        return destroyMethod;
    }

    @Override
    public List<?> getConstructorArgumentValues() {
        return constructorArgumentValues;
    }

    @Override
    public void setConstructorArgumentValues(List<?> constructorArgumentValues) {
        this.constructorArgumentValues = constructorArgumentValues;
    }

    @Override
    public Constructor getConstructor() {
        return constructor;
    }

    @Override
    public void setConstructor(Constructor constructor) {
        this.constructor = constructor;
    }

    @Override
    public Method getFactoryMethod() {
        return factoryMethod;
    }

    @Override
    public void setFactoryMethod(Method factoryMethod) {
        this.factoryMethod = factoryMethod;
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    @Override
    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public String toString() {
        return "GenericBeanDefinition{" +
                "beanClass=" + beanClass +
                ", factoryMethodName='" + factoryMethodName + '\'' +
                ", factoryMethod=" + factoryMethod +
                ", factoryBeanName='" + factoryBeanName + '\'' +
                ", initMethod='" + initMethod + '\'' +
                ", destroyMethod='" + destroyMethod + '\'' +
                ", constructorArgumentValues=" + constructorArgumentValues +
                ", propertyValues=" + propertyValues +
                ", constructor=" + constructor +
                ", scope='" + scope + '\'' +
                '}';
    }
}
