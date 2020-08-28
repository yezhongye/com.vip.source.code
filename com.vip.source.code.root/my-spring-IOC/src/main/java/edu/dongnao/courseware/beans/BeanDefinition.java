package edu.dongnao.courseware.beans;

import org.apache.commons.lang3.StringUtils;

/**
 * Bean定义，用来指定Bean构建信息接口
 */
public interface BeanDefinition {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    /**
     * 获取Bean的类名
     * @return
     */
    Class<?> getBeanClass();

    /**
     * 
     * @param beanClass
     */
    void setBeanClass(Class<?> beanClass);
    
    String getScope();
    void setScope(String scope);
    boolean isSingleton();
    boolean isPrototype();
    String getFactoryMethodName();
    void setFactoryMethodName(String factoryMethodName);
    String getFactoryBeanName();
    void setFactoryBeanName(String factoryBeanName);
    void setInitMethod(String initMethod);
    String getInitMethod();
    void setDestroyMethod(String destroyMethod);
    String getDestroyMethod();
     
    
    default boolean validate() {
        // 没有BeanCalss信息，只能通过成员工厂来构建对象
        if(getBeanClass() == null) {
            if(StringUtils.isBlank(getFactoryBeanName()) || StringUtils.isBlank(getFactoryMethodName())){
                return false;
            }
        }
        
        // Class存在的情况，还指定FactoryBeanName，构建对象的方式冲突
        if(getBeanClass() != null && StringUtils.isNotBlank(getFactoryBeanName())) {
            return false;
        }
        return true;
    };
}
