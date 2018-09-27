package com.maven.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title: MyServletContextListener
 * </p>
 * <p>
 * Description:
 * 实现ServletContextListener接口的ServletContextListener类型监听器(用于添加系统初始化参数)
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月20日 上午11:29:39
 */
public class MyServletContextListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(MyServletContextListener.class);

	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		 log.debug("********************MyServletContextListener监听器初始化********************");
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
