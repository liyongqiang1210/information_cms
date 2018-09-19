package com.maven.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class testListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("********************测试监听器销毁********************");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("********************测试监听器启动********************");
	}


}
