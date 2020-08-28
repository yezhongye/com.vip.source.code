package edu.dongnao.courseware.beans;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, Closeable {
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();
    private Set<String> buildingBeans = Collections.newSetFromMap(new ConcurrentHashMap());
    
    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition bd = beanDefinitionMap.get(beanName);
        Object bean = doGetBean(beanName);
        // 完成属性注入
        setPropertyDIVAlues(bd, bean);
        
        // 开始Bean生命周期
        if(StringUtils.isNotBlank(bd.getInitMethod())) {
            doInitMethod(bean, bd);
        }
        return bean;
    }

    protected Object doGetBean(String beanName) throws Exception {
        Objects.requireNonNull(beanName, "beanName不能为空");

        // 先去缓存里面判断一下beanName对应的对象已经创建好了
        Object bean = beanMap.get(beanName);
        if(bean != null) {return bean;}
        
        // 构建的方式有三种：构造函数、静态工厂、成员工厂
        BeanDefinition bd = beanDefinitionMap.get(beanName);
        Objects.requireNonNull(bd, "找不到["+beanName+"]的bean定义信息");
        Class<?> type = bd.getBeanClass();

        // 记录正在创建的Bean
        Set<String> ingBeans = this.buildingBeans;
        
        // 检测魂环依赖
        if(ingBeans.contains(beanName)){
            throw new Exception(beanName + " 循环依赖！" + ingBeans);
        }
        // 记录正在创建的Bean
        ingBeans.add(beanName);
        
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
        
        // 实例创建完成后进行删除
        ingBeans.remove(beanName);
        
        // 对单例bean的处理
        if(bd.isSingleton()){
            beanMap.put(beanName, bean);
        }
        
        return bean;
    }

    private void setPropertyDIVAlues(BeanDefinition bd, Object instance) throws Exception {
        if (CollectionUtils.isEmpty(bd.getPropertyValues())) {
            return;
        }
        for (PropertyValue pv : bd.getPropertyValues()) {
            if (StringUtils.isBlank(pv.getName())) {
                continue;
            }
            Class<?> clazz = instance.getClass();
            Field p = clazz.getDeclaredField(pv.getName());

            p.setAccessible(true);

            Object rv = pv.getValue();
            Object v = null;
            if (rv == null) {
                v = null;
            } else if (rv instanceof BeanReference) {
                v = this.doGetBean(((BeanReference) rv).getBeanName());
            } else if (rv instanceof Object[]) {
                // TODO 处理集合中的bean引用
            } else if (rv instanceof Collection) {
                // TODO 处理集合中的bean引用
            } else if (rv instanceof Properties) {
                // TODO 处理properties中的bean引用
            } else if (rv instanceof Map) {
                // TODO 处理Map中的bean引用
            } else {
                v = rv;
            }

            p.set(instance, v);

        }
    }

    private void doInitMethod(Object bean, BeanDefinition bd) throws Exception {
        Method method = bean.getClass().getMethod(bd.getInitMethod(), null);
        method.invoke(bean, null);
    }

    private Object createBeanByFactoryBean(BeanDefinition bd) throws Exception {
        String factoryBeanName = bd.getFactoryBeanName();
        Object factoryBean = getBean(factoryBeanName);
        Object[] realArgs = getRealVaues(bd.getConstructorArgumentValues());
        Method method = determineFactoryMethod(bd, realArgs, factoryBean.getClass());
        return method.invoke(factoryBean, realArgs);
    }

    private Object createBeanByStaticFactory(BeanDefinition bd) throws Exception {
        Class<?> type = bd.getBeanClass();
        Object[] realArgs = getRealVaues(bd.getConstructorArgumentValues());
        Method method = determineFactoryMethod(bd, realArgs, type);
        return method.invoke(type, realArgs);
    }

    private Method determineFactoryMethod(BeanDefinition bd, Object[] args, Class<?> type) throws Exception {
        if (type == null) {
            type = bd.getBeanClass();
        }
        String methodName = bd.getFactoryMethodName();
        
        if(args  == null) {
            return type.getMethod(methodName, null);
        }
        Method m = null;

        // 对于原型bean,从第二次开始获取bean实例时，可直接获得第一次缓存的构造方法。
        m = bd.getFactoryMethod();
        if (m != null) {
            return m;
        }
        
        // 根据参数类型获取精确匹配的方法
        Class[] paramTypes = new Class[args.length];
        int j = 0;
        for (Object p : args) {
            paramTypes[j++] = p.getClass();
        }
        try{
            m = type.getMethod(methodName, paramTypes);
        }catch (NoSuchMethodException e){
            // 不做任何处理
            m = null;
        }
        if (m == null) {
            // 判断逻辑：先判断参数数量，再依次比对形参类型与实参类型
            outer: for (Method m0 : type.getMethods()) {
                if (!m0.getName().equals(methodName)) {
                    continue;
                }
                Class<?>[] paramterTypes = m0.getParameterTypes();
                if (paramterTypes.length == args.length) {
                    for (int i = 0; i < paramterTypes.length; i++) {
                        if (!paramterTypes[i].isAssignableFrom(args[i].getClass())) {
                            continue outer;
                        }
                    }

                    m = m0;
                    break outer;
                }
            }
        }

        if (m != null) {
            // 对于原型bean,可以缓存找到的方法，方便下次构造实例对象。在BeanDefinfition中获取设置所用方法的方法。
            // 同时在上面增加从beanDefinition中获取的逻辑。
            if (bd.isPrototype()) {
                bd.setFactoryMethod(m);
            }
            return m;
        } else {
            throw new Exception("不存在对应的构造方法！" + bd);
        }
    }

    private Object createBeanByConstructor(BeanDefinition bd) throws Exception {
        Object instance = null;
        if(CollectionUtils.isEmpty(bd.getConstructorArgumentValues())) {
            instance = bd.getBeanClass().newInstance();
        }else{
            Object[] args = getConstructorArgumentValues(bd);
            if(args == null) {
                instance = bd.getBeanClass().newInstance();
            }else {
                return determineConstructor(bd, args).newInstance(args);
            }
        }
        return instance;
    }
    
    private Constructor determineConstructor(BeanDefinition bd, Object[] args) throws Exception {
        Constructor ct = null;
        if(args == null) {return bd.getBeanClass().getConstructor(null);}
        Class<?>[] paramType = new Class[args.length];
        
        // 对于原型Bean，从第二次开始获取Bean实例时，可以直接从第一次缓存中获取构造方法
        ct = bd.getConstructor();
        if(ct != null) {return ct;}
        
        
        // 根据参数类型获取构造方法
        int j = 0;
        for(Object p : args) {
            paramType[j++] = p.getClass();
        }
        ct = bd.getBeanClass().getConstructor(paramType);
        
        if(ct == null) {
            Constructor<?>[]  cts = bd.getBeanClass().getConstructors();
            // 判断逻辑：先判断参数数量，依次判断形参跟实参进行类型匹配
            outer: for(Constructor<?> c : cts) {
                Class<?>[] paramterTypes = c.getParameterTypes();
                if(paramterTypes.length == args.length) {
                    for(int i = 0; i < paramterTypes.length; i++) {
                        if(!paramterTypes[i].isAssignableFrom(args[i].getClass())) {
                            continue outer;
                        }
                    }
                    ct = c;
                    break outer;
                }
            }
        }
        
        if(ct != null) {
            if(bd.isPrototype()) {
                bd.setConstructor(ct);
            }
        }else {
            throw new Exception("找不到对应的构造方法："+bd);
        }
        return ct;
    }
    
    
    private Object[] getConstructorArgumentValues(BeanDefinition bd) throws Exception {
        List<?> args = bd.getConstructorArgumentValues();
        return getRealVaues(args);
    }

    private Object[] getRealVaues(List<?> args) throws Exception {
        if(CollectionUtils.isEmpty(args)) {return null;}
        Object[] values = new Object[args.size()];

        Object v = null;
        for(int i = 0; i < args.size(); i++){
            Object rv = args.get(i);
            if(rv == null) {
                v = null;
            }else if (rv instanceof  BeanReference){
                v = doGetBean(((BeanReference) rv).getBeanName());
            }else if (rv instanceof Object[]) {
                // TODO 处理集合中的bean引用
            } else if (rv instanceof Collection) {
                // TODO 处理集合中的bean引用
            } else if (rv instanceof Properties) {
                // TODO 处理properties中的bean引用
            } else if (rv instanceof Map) {
                // TODO 处理Map中的bean引用
            } else {
                v = rv;
            }
            values[i] = v;
        }
        return values;
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
