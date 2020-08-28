package edu.dongnao.courseware.beans;

/**
 * Bean定义注册接口，用来完成Bean定义和Bean工厂之间沟通
 */
public interface BeanDefinitionRegistry {
    /**
     * 想Bean工厂，注册Bean定义信息
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegisterException;

    /**
     * 获取已经注册的BeanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 是否包含了已经定义的beanName
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);
}
