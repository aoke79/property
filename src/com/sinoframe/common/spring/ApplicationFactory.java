package com.sinoframe.common.spring;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ApplicationFactory implements ApplicationContextAware {

	private static ApplicationContext ctx = null;

	public static ApplicationContext getContext() {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext(
					"classpath*:applicationContext-*.xml");
		}
		return ctx;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ctx = context;
	}

	public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}
	
	public static DataSource getDataSource() {
		return (DataSource) getContext().getBean("dataSource");
	}
	
	public static DataSource getDataSource(String dataSourceName) {
		return (DataSource) getContext().getBean(dataSourceName);
	}

	
	

}
