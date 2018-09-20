package com.maven.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * Title: MySessionScanerListener
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月20日 下午3:26:02
 */
public class MySessionScanerListener implements HttpSessionListener, ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("MySessionScanerListener初始化");
	}

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("MySessionScanerListener初始化session");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		
	}

}
