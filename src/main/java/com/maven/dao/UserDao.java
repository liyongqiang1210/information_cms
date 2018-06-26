package com.maven.dao;

import java.util.List;

import com.maven.entity.User;

/**
 * 
 * @author liyongqiang
 *
 */
public interface UserDao {
	public int createUser(User user);

	public int updateUser(User user);

	public int deleteUser(int userId);

	public User findOne(int userId);

	public List<User> findAll(int limit, int offset, String userName, String createTime);

	public User findByUsername(String username);
	
	public int login(User user);

	public List<User> getUserList();
	
	public int updateUserState(User user);
	
}
