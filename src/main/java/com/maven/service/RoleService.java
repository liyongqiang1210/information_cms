package com.maven.service;

import java.util.List;
import java.util.Set;

import com.maven.model.pojo.Role;

/**
 * 
 * @author liyongqiang
 *
 */
public interface RoleService {

	public void createRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(int roleId);

	public Role findRoleById(int roleId);

	public List<Role> findAll();
	
	public String findRoleNameByUserName(String username);

	/**
	 * 根据角色编号得到角色标识符列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findRoles(String username);

	/**
	 * 根据角色编号得到权限字符串列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findPermissions(String[] roleIds);
}
