package com.maven.cache;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.maven.util.JedisUtil;

/**
 * 
 * <p>Title: RedisCache</p>  
 * <p>Description: </p>  
 * @author liyongqiang  
 * @date 2018年4月30日  
 * @version 1.0
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

	@Resource
	private JedisUtil jedisUtil;
	// 缓存前缀
	private final String CACHE_PREFIX = "liyongqiang-cache:";
	
	// 获取key的方法
	private byte[] getKey(K k){
		if(k instanceof String){
			return (CACHE_PREFIX + k).getBytes();
		}
		return SerializationUtils.serialize(k);
	}
	
	public void clear() throws CacheException {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public V get(K k) throws CacheException {
		// 根据key值取出value
		byte[] value = jedisUtil.get(getKey(k));
		if(value != null){
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}

	public Set<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(K k, V v) throws CacheException {
		byte[] key = getKey(k);
		byte[] value = SerializationUtils.serialize(v);
		jedisUtil.set(key, value); // 存储到redis中
		jedisUtil.expire(key, 600); // 设置超时时间
		return v;
	}

	@SuppressWarnings("unchecked")
	public V remove(K k) throws CacheException {
		byte[] key = getKey(k);
		jedisUtil.del(key);
		byte[] value = jedisUtil.get(key);
		if(value != null){
			return (V) SerializationUtils.deserialize(value);
		}
		return null;
	}

	public int size() {
		Set<byte[]> keys = jedisUtil.keys(CACHE_PREFIX);
		return keys.size();
	}

	public Collection<V> values() {
		return null;
	}

}
