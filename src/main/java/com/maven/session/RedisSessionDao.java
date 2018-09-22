package com.maven.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.maven.util.JedisUtil;

/**
 * <p>
 * Title: RedisSessionDao
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年4月27日
 * @version 1.0
 */
@Component
public class RedisSessionDao extends AbstractSessionDAO {

	@Resource
	private JedisUtil jedisUtil;

	private final String SHIRO_SESSION_PREFIX = "liyongqiang-session:";
	
	private static final Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);

	// 创建key
	private byte[] getKey(String key) {
		return (SHIRO_SESSION_PREFIX + key).getBytes();
	}

	@SuppressWarnings("null")
	public void delete(Session session) {

		if (session == null && session.getId() == null) {
			return;
		}

		byte[] key = getKey(session.getId().toString());
		jedisUtil.del(key);

	}

	public Collection<Session> getActiveSessions() {

		Set<byte[]> keys = jedisUtil.keys(SHIRO_SESSION_PREFIX);
		Set<Session> sessions = new HashSet<Session>();
		if (CollectionUtils.isEmpty(keys)) {
			return sessions;
		}
		// 遍历key值
		for (byte[] key : keys) {

			// 根据key值获取session对象
			Session session = (Session) SerializationUtils.deserialize(jedisUtil.get(key));
			// 将session对象存储到sessions集合中
			sessions.add(session);
		}
		// 获取当前在线人数
		logger.debug(new Date() + "当前在线人数：" + sessions.size());
		return sessions;
	}

	public void update(Session session) throws UnknownSessionException {

		saveSession(session);
	}

	@Override
	protected Serializable doCreate(Session session) {

		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);// 将session与sessionId捆绑到一起
		saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {

		if (sessionId == null) {
			return null;
		}
		byte[] key = getKey(sessionId.toString());
		byte[] value = jedisUtil.get(key);
		Session session = (Session) SerializationUtils.deserialize(value); // 获取session
		return session;
	}

	/**
	 * 保存session的方法
	 * 
	 * @param session
	 */
	private void saveSession(Session session) {

		byte[] key = getKey(session.getId().toString());
		byte[] value = SerializationUtils.serialize(session);
		jedisUtil.set(key, value); // 设置session
		jedisUtil.expire(key, 1800); // 设置session的超时时间30分钟
	}
}
