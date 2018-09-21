package com.maven.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Title: MyShiroSessionListener
 * </p>
 * <p>
 * Description:spring集成shiro之后的session监听器
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月21日 上午10:36:59
 */
@Component
public class MyShiroSessionListener implements SessionListener {

	private static final Logger logger = LoggerFactory.getLogger(MyShiroSessionListener.class);
	// 在线用户人数
	private static int TOTAL_ONLINE_USERS = 0;

	public void onStart(Session session) { // 会话创建时触发
		if (TOTAL_ONLINE_USERS == 0) {
			TOTAL_ONLINE_USERS = 1;
			session.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
		} else {
			TOTAL_ONLINE_USERS++;
			session.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
		}
		logger.debug("session创建：" + session.getId());
		logger.debug("当前在线人数：" + TOTAL_ONLINE_USERS);
	}

	public void onStop(Session session) { // 会话退出时触发
		if (TOTAL_ONLINE_USERS == 0) {
			session.setAttribute("TOTAL_ONLINE_USERS", 0);
		} else {
			TOTAL_ONLINE_USERS--;
			session.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
		}
		logger.debug("当前在线人数：" + TOTAL_ONLINE_USERS);
		logger.debug("session退出：" + session.getId());
	}

	public void onExpiration(Session session) { // 会话过期时触发
		if (TOTAL_ONLINE_USERS == 0) {
			session.setAttribute("TOTAL_ONLINE_USERS", 0);
		} else {
			TOTAL_ONLINE_USERS--;
			session.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
		}
		logger.debug("当前在线人数：" + TOTAL_ONLINE_USERS);
		logger.debug("session过期：" + session.getId());
	}

}
