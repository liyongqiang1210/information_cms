package com.maven.util;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 
 * <p>
 * Title: JedisUtil
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
public class JedisUtil {

	@Resource
	private JedisPool jedisPool;

	// 获取session连接
	private Jedis getResource() {
		return jedisPool.getResource();
	}

	/**
	 * 向redis中添加值
	 * 
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value) {

		Jedis jedis = getResource();
		try {
			jedis.set(key, value); // 向redis中添加值
		} finally {
			jedis.close(); // 关闭redis连接
		}
	}

	/**
	 * 设置session的超时时间
	 * 
	 * @param key
	 *            session的key值
	 * @param seconds
	 *            session的超时时间
	 * 
	 */
	public void expire(byte[] key, int seconds) {

		Jedis jedis = getResource();
		try {
			jedis.expire(key, seconds); // 设置session的超时时间
		} finally {
			jedis.close(); // 关闭redis连接
		}
	}

	/**
	 * 根据key获取value得方法
	 * 
	 * @param key
	 *            session的key值
	 * @return 该key在session中存储的value值
	 */
	public byte[] get(byte[] key) {

		Jedis jedis = getResource();
		try {
			byte[] value = jedis.get(key);
			return value; // 获取session
		} finally {
			jedis.close(); // 关闭redis连接
		}
	}

	/**
	 * 根据key值删除session
	 * 
	 * @param key
	 */
	public void del(byte[] key) {

		Jedis jedis = getResource();
		try {
			jedis.del(key);
		} finally {
			jedis.close(); // 关闭redis连接
		}
	}

	/**
	 * 根据session前缀获取存活的session集合
	 * 
	 * @param SHIRO_SESSION_PREFIX
	 *            session前缀
	 * @return 存活的session集合
	 */
	public Set<byte[]> keys(String SHIRO_SESSION_PREFIX) {

		Jedis jedis = getResource();
		try {

			Set<byte[]> keys = jedis.keys((SHIRO_SESSION_PREFIX + "*").getBytes());
			return keys;
		} finally {
			jedis.close(); // 关闭redis连接
		}

	}

}
