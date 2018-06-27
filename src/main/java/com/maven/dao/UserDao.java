package com.maven.dao;

import java.util.List;

import com.maven.model.pojo.User;
import com.maven.model.query.QueryUser;

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

	public List<User> findAll(QueryUser qu);

	public User findByUsername(String username);
	
	public int login(User user);

	public List<User> getUserList();
	
	public int updateUserState(User user);
	
}
