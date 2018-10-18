package com.maven.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.maven.dao.RoleDao;
import com.maven.dao.UserDao;
import com.maven.model.pojo.Role;
import com.maven.model.pojo.User;
import com.maven.model.query.QueryRole;
import com.maven.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;

	public void createRole(Role role) {
		roleDao.createRole(role);
	}

	public void updateRole(Role role) {

		roleDao.updateRole(role);
	}

	public void deleteRole(int roleId) {

		roleDao.deleteRole(roleId);

	}

	public Role findRoleById(int roleId) {

		return roleDao.findRoleById(roleId);
	}

	public List<Role> findAll(int limit, int offset, String roleName, int available) {
		QueryRole qr = new QueryRole();
		qr.setLimit(limit);
		qr.setOffset(offset);
		qr.setRoleName(roleName);
		return roleDao.findAll(qr);
	}

	public Set<String> findRoles(String username) {

		// 获取角色id字符串
		User user = userDao.findByUsername(username);
		String roleIds = user.getRoleIds();

		// 根据角色id查询角色名
		Set<String> roleSet = new HashSet<String>();
		try {
			String[] ids = roleIds.split(",");
			for (String str : ids) {
				Integer id = Integer.valueOf(str);
				if (id != null && id != 0) {
					String roleName = roleDao.findRoleNameById(id);
					roleSet.add(roleName);
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return roleSet;
	}

	public Set<String> findPermissions(String[] roleIds) {

		return null;
	}

	public int queryRoleNameIsExist(String rolename) {

		return roleDao.queryRoleNameIsExist(rolename);
	}

	public void updateRoleState(Role role) {

		roleDao.updateRoleState(role);
	}

	public int queryRoleCount() {

		return roleDao.queryRoleCount();
	}

	@Transactional
	public void deleteSelectedRole(String ids) {
		try {
			String[] split = ids.split(",");
			for (String str : split) {
				Integer roleId = Integer.valueOf(str);
				roleDao.deleteRole(roleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚事务
		}
	}

}
