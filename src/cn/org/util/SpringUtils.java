/**
 * 
 */
package cn.org.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 侯浩军
 * 
 * 3:24:09 PM
 */
public class SpringUtils implements ApplicationContextAware {// extends// ApplicationObjectSupport{

	private static ApplicationContext context = null;
	private static SpringUtils stools = null;

	public synchronized static SpringUtils init() {
		if (stools == null) {
			stools = new SpringUtils();
		}
		return stools;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}

	public synchronized static Object getBean(String beanName) {
		Object bean = context.getBean(beanName);
		return context.getBean(beanName);
	}
}