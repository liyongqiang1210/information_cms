package com.maven.service;

import java.util.List;
import java.util.Set;

import com.maven.entity.User;

/**
 * 
 * @author liyongqiang
 *
 */
public interface UserService {

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public String createUser(User user);

	public String updateUser(User user);

	public String deleteUser(int userId);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(int userId, String newPassword);

	public User findOne(int userId);

	public List<User> findAll();

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String username);
	
	/**
	 * 测试登录方法
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int login(User user);
	
	/**
	 * 修改用户是否启用
	 * 
	 * @param user
	 * @return
	 */
	public String updateUserState(User user);
	
}
