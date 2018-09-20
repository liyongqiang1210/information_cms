package com.maven.listener;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 
 * <p>
 * Title: MyServletContextListener
 * </p>
 * <p>
 * Description: 实现ServletContextListener接口的ServletContextListener类型监听器
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月20日 上午11:29:39
 */
public class MyServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {

		System.out.println("********************测试监听器启动********************");
		// 获取项目根路径
		// String contextPath =
		// servletContextEvent.getServletContext().getRealPath("/");
		// System.out.println("项目根路径是：" + contextPath);

		// 设置定时器
		// final Timer timer = new Timer();
		// timer.schedule(new TimerTask() {
		//
		// @Override
		// public void run() {
		// System.out.println("当前系统时间：" + new Date());
		// }
		//
		// }, 0, 10000);
	}

}
