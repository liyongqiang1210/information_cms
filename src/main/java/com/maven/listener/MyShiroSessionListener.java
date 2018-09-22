package com.maven.listener;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.maven.session.RedisSessionDao;

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
public class MyShiroSessionListener implements SessionListener {

	@Resource
	private RedisSessionDao redisSessionDao;

	private static final Logger logger = LoggerFactory.getLogger(MyShiroSessionListener.class);

	public void onStart(Session session) { // 会话创建时触发
		logger.debug("session创建：" + session.getId());
	}

	public void onStop(Session session) { // 会话退出时触发
		logger.debug("session退出：" + session.getId());
		// 删除session
		redisSessionDao.delete(session);
	}

	public void onExpiration(Session session) { // 会话过期时触发
		logger.debug("session过期：" + session.getId());
		// 删除session
		redisSessionDao.delete(session);
	}

}
