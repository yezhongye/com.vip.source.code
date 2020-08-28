package v1.ioc;

import edu.dongnao.courseware.beans.BeanDefinition;
import edu.dongnao.courseware.beans.DefaultBeanFactory;
import edu.dongnao.courseware.beans.GenericBeanDefinition;
import org.junit.AfterClass;
import org.junit.Test;

public class DefaultBeanFactoryTest {
    static DefaultBeanFactory bf = new DefaultBeanFactory();

    @Test
    public void testRegist() throws Exception {

        GenericBeanDefinition bd = new GenericBeanDefinition();

        bd.setBeanClass(Lad.class);
        bd.setScope(BeanDefinition.SCOPE_SINGLETON);
        // bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        bd.setInitMethod("init");
        bd.setDestroyMethod("destroy");

        bf.registerBeanDefinition("lad", bd);

    }

    @Test
    public void testRegistStaticFactoryMethod() throws Exception {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(BoyFactory.class);
        bd.setFactoryMethodName("getBean");
        bf.registerBeanDefinition("staticBoyFactory", bd);
    }

    @Test
    public void testRegistFactoryMethod() throws Exception {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClass(BoyFactoryBean.class);
        String fbname = "factoryBean";
        bf.registerBeanDefinition(fbname, bd);

        bd = new GenericBeanDefinition();
        bd.setFactoryBeanName(fbname);
        bd.setFactoryMethodName("buildBoy");
        bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        bf.registerBeanDefinition("factoryBoy", bd);
    }

    @AfterClass
    public static void testGetBean() throws Exception {
        System.out.println("构造方法方式------------");
        for (int i = 0; i < 3; i++) {
            Boy boy = (Boy) bf.getBean("lad");
            boy.sayLove();
        }

        System.out.println("静态工厂方法方式------------");
        for (int i = 0; i < 3; i++) {
            Boy ab = (Boy) bf.getBean("staticBoyFactory");
            ab.sayLove();
        }

        System.out.println("工厂方法方式------------");
        for (int i = 0; i < 3; i++) {
            Boy ab = (Boy) bf.getBean("factoryBoy");
            ab.sayLove();
        }

        bf.close();
    }
    
}
