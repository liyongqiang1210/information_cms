package com.maven.dao;

import java.util.List;
import java.util.Set;

import com.maven.entity.Role;

/**
 * 
 * @author liyongqiang
 * 
 */
public interface RoleDao {

	public void createRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(int roleId);

	public String findRoleNameById(int roleId);

	public List<Role> findAll();

	public Set<String> findRoles(String username);

	public Role findRoleById(int roleId);
	
	public String findRoleNameByUserName(String username);
}
