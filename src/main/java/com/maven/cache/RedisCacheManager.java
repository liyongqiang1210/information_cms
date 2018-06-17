package com.maven.cache;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * 缓存管理器
 * 
 * <p>Title: RedisCacheManager</p>  
 * <p>Description: </p>  
 * @author liyongqiang  
 * @date 2018年4月30日  
 * @version 1.0
 */
public class RedisCacheManager implements CacheManager {

	@SuppressWarnings("rawtypes")
	@Resource
	private RedisCache redisCache;

	@SuppressWarnings("unchecked")
	public <K, V> Cache<K, V> getCache(String string) throws CacheException {
		return redisCache;
	}

}
