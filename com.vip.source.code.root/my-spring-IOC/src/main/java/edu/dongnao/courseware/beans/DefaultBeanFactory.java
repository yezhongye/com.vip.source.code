package edu.dongnao.courseware.beans;

import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, Closeable {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();
    
    @Override
    public Object getBean(String beanName) throws Exception {
        return doGetBean(beanName);
    }

    private Object doGetBean(String beanName) throws Exception {
        Objects.requireNonNull(beanName, "beanName不能为空");

        // 先去缓存里面判断一下beanName对应的对象已经创建好了
        Object bean = beanMap.get(beanName);
        if(bean != null) {return bean;}
        
        // 构建的方式有三种：构造函数、静态工厂、成员工厂
        BeanDefinition bd = beanDefinitionMap.get(beanName);
        Objects.requireNonNull(bd, "找不到["+beanName+"]的bean定义信息");
        Class<?> type = bd.getBeanClass();
        if(type != null){
            // 通过构造函数
            if(StringUtils.isBlank(bd.getFactoryMethodName())) {
                bean = createBeanByConstructor(bd);
            }else{ // 通过静态工厂方式构建对象
                bean = createBeanByStaticFactory(bd);
            }
        }else{
            // 成员工厂方式构建对象
            bean = createBeanByFactoryBean(bd);
        }
        
        // 开始Bean生命周期
        if(StringUtils.isNotBlank(bd.getInitMethod())) {
            doInitMethod(bean, bd);
        }
        
        // 对单例bean的处理
        if(bd.isSingleton()){
            beanMap.put(beanName, bean);
        }
        
        return bean;
    }

    private void doInitMethod(Object bean, BeanDefinition bd) throws Exception {
        Method method = bean.getClass().getMethod(bd.getInitMethod(), null);
        method.invoke(bean, null);
    }

    private Object createBeanByFactoryBean(BeanDefinition bd) throws Exception {
        String factoryBeanName = bd.getFactoryBeanName();
        Object factoryBean = getBean(factoryBeanName);
        Method method = factoryBean.getClass()
                .getMethod(bd.getFactoryMethodName(), null);
        return method.invoke(factoryBean, null);
    }

    private Object createBeanByStaticFactory(BeanDefinition bd) throws Exception {
        Class<?> type = bd.getBeanClass();
        Method method = type.getMethod(bd.getFactoryMethodName(), null);
        return method.invoke(type, null);
    }

    private Object createBeanByConstructor(BeanDefinition bd) throws Exception {
        Object instance = bd.getBeanClass().newInstance();
        return instance;
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegisterException {
        Objects.requireNonNull(beanName, "注册bean需要指定beanName");
        Objects.requireNonNull(beanDefinition, "注册bean需要指定beanDefinition");
        
        if(!beanDefinition.validate()) {
            throw new BeanDefinitionRegisterException("名字为["+beanName+"]的bean定义不合法："+beanDefinition);
        }
        
        if(containsBeanDefinition(beanName)) {
            throw new BeanDefinitionRegisterException("名字为["+beanName+"]已存在："+getBeanDefinition(beanName));
        }
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public void close() throws IOException {
        // 针对单例Bean执行销毁方法
        for(Map.Entry<String, BeanDefinition> e : beanDefinitionMap.entrySet()) {
            String beanName = e.getKey();
            BeanDefinition definition = e.getValue();
            
            if(definition.isSingleton() && StringUtils.isNotBlank(definition.getDestroyMethod())) {
                Object instance = beanMap.get(beanName);
                if(instance == null) {continue;}
                
                Method m = null;
                try {
                    m = instance.getClass().getMethod(definition.getDestroyMethod(), null);
                    m.invoke(instance, null);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
