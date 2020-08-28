package edu.dongnao.courseware.beans;

/**
 * Bean工厂，IOC容器最顶层的抽象
 */
public interface BeanFactory {
    /**
     * 用户端获取Bean的方法
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws Exception;
}
