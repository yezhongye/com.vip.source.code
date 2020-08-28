package edu.dongnao.courseware.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 提前构建单例bean的工程
 */
public class PreBuildBeanFactory extends DefaultBeanFactory {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private List<String> beanNames = new ArrayList<>();

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
			throws BeanDefinitionRegisterException {
		super.registerBeanDefinition(beanName, beanDefinition);
		synchronized (beanNames) {
			beanNames.add(beanName);
		}
	}

	public void preInstantiateSingletons() throws Exception {
		synchronized (beanNames) {
			for (String name : beanNames) {
				BeanDefinition bd = this.getBeanDefinition(name);
				if (bd.isSingleton()) {
					this.doGetBean(name);
					if (logger.isDebugEnabled()) {
						logger.debug("preInstantiate: name=" + name + " " + bd);
					}
				}
			}
		}
	}
}
