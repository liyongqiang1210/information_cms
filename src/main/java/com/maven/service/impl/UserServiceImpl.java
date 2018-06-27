package com.maven.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maven.cache.RedisCache;
import com.maven.dao.UserDao;
import com.maven.model.pojo.User;
import com.maven.model.query.QueryUser;
import com.maven.service.UserService;
import com.maven.util.Md5Util;
import com.maven.util.MessageUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisCache<String, List<User>> redisCache;

	public String createUser(User user) {

		// MD5加密
		String password = user.getPassword();
		String salt = Md5Util.getRandomSalt(6);
		Md5Hash md5Hash = new Md5Hash(password, salt);
		user.setSalt(salt);
		user.setPassword(md5Hash.toString());

		if (userDao.createUser(user) == 1) {
			return MessageUtil.SUCCESS_MESSAGE;
		}

		return MessageUtil.ERROR_MESSAGE;
	}

	public String updateUser(User user) {
		if (userDao.updateUser(user) == 1) {
			return MessageUtil.SUCCESS_MESSAGE;
		}

		return MessageUtil.ERROR_MESSAGE;
	}

	public String deleteUser(int userId) {

		if (userDao.deleteUser(userId) == 1) {
			return MessageUtil.SUCCESS_MESSAGE;
		}

		return MessageUtil.ERROR_MESSAGE;
	}

	/**
	 * 此处需要添加事务的支持
	 * 
	 * @param userIds
	 * @return
	 */
	public String deleteUsers(String userIds) {

		try {
			String[] ids = userIds.split(",");
			for (String string : ids) {
				Integer id = Integer.valueOf(string);
				userDao.deleteUser(id);
			}
			return MessageUtil.SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MessageUtil.ERROR_MESSAGE;
	}

	public void changePassword(int userId, String newPassword) {

	}

	public User findOne(int userId) {
		return null;
	}

	public List<User> findAll(QueryUser qu) {

		String key = "findAllUser";
		List<User> list = redisCache.get(key);
		if (list != null && list.size() != 0) {
			return list;
		}
		list = userDao.findAll(qu);
		redisCache.put("findAllUser", list);
		return list;
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public Set<String> findRoles(String username) {
		return null;
	}

	public Set<String> findPermissions(String username) {
		return null;
	}

	public int login(User user) {

		return userDao.login(user);
	}

	public String updateUserState(User user) {

		int state = userDao.updateUserState(user);
		if (state == 1) {
			return MessageUtil.SUCCESS_MESSAGE;
		}
		return MessageUtil.ERROR_MESSAGE;
	}

}
