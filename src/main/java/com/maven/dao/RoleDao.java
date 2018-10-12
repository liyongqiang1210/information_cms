package com.maven.dao;

import java.util.List;
import java.util.Set;

import com.maven.model.pojo.Role;
import com.maven.model.query.QueryRole;

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

	public List<Role> findAll(QueryRole qr);

	public Set<String> findRoles(String username);

	public Role findRoleById(int roleId);
	
	public String findRoleNameByUserName(String username);

	public void updateRoleState(Role role);

	public int queryRoleCount();
}
