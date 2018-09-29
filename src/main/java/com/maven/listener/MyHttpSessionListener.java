package com.maven.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <p>
 * Title: MyHttpSessionListener
 * </p>
 * <p>
 * Description: 实现HttpSessionListener接口的监听器
 * 此监听器功能用于实现监听在线用户人数功能(使用时注意只有在使用jsp页面时才有效)
 * (shiro中是不支持使用此类监听器的)
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月20日 上午11:31:03
 */
public class MyHttpSessionListener implements HttpSessionListener {

	// 当前在线用户人数
	public static int TOTAL_ONLINE_USERS = 0;

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//		ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
//		TOTAL_ONLINE_USERS = (int) servletContext.getAttribute("TOTAL_ONLINE_USERS");
//		// 如果用户登录,TOTAL_ONLINE_USERS加1
//		TOTAL_ONLINE_USERS++;
//		servletContext.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
//		System.out.println("当前在线用户：" + TOTAL_ONLINE_USERS);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//		ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
//		TOTAL_ONLINE_USERS = (int) servletContext.getAttribute("TOTAL_ONLINE_USERS");
//		// 如果用户退出,TOTAL_ONLINE_USERS减1
//		if (TOTAL_ONLINE_USERS == 0) {
//			servletContext.setAttribute("TOTAL_ONLINE_USERS", 0);
//		} else {
//			TOTAL_ONLINE_USERS--;
//			servletContext.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
//		}
//		System.out.println("当前在线用户：" + TOTAL_ONLINE_USERS);
	}

}
