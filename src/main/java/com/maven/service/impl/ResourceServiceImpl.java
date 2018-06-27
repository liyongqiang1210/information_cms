package com.maven.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maven.dao.ResourceDao;
import com.maven.dao.RoleDao;
import com.maven.dao.UserDao;
import com.maven.model.pojo.Resource;
import com.maven.model.pojo.Role;
import com.maven.model.pojo.User;
import com.maven.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;
	
	public void createResource(Resource resource) {
		
		resourceDao.createResource(resource);
	}

	public void updateResource(Resource resource) {
		
		resourceDao.updateResource(resource);
	}

	public void deleteResource(int resourceId) {

		resourceDao.deleteResource(resourceId);
	}

	public List<Resource> findAll() {
		
		return resourceDao.findAll();
	}

	public Set<String> findPermissions(String username) {
		
		Set<String> set = new HashSet<String>();
		
		try {
			// 获取角色id字符串
			User user = userDao.findByUsername(username);
			String roleIds = user.getRoleIds();
			
			// 根据角色id获取资源id字符串
			String[] roleIdsArray = roleIds.split(",");
			for (String roleIdString : roleIdsArray) {
				Integer roleId = Integer.valueOf(roleIdString);
				Role role = roleDao.findRoleById(roleId);
				String resourceIds = role.getResourceIds();
				
				// 根据资源id获取权限
				String[] resourceIdsArray = resourceIds.split(",");
				for (String resourceIdString : resourceIdsArray) {
					Integer resourceId = Integer.valueOf(resourceIdString);
					set.add(resourceDao.findPermissionById(resourceId));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return set;
	}

	public List<Resource> findMenus(Set<String> permissions) {

		return null;
	}

	public List<Resource> findResourceByName(Resource resource) {
		
		return resourceDao.findResourceByName(resource);
	}

}
