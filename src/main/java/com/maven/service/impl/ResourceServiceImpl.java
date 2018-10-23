package com.maven.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.maven.dao.ResourceDao;
import com.maven.dao.RoleDao;
import com.maven.dao.UserDao;
import com.maven.model.pojo.Resource;
import com.maven.model.pojo.Role;
import com.maven.model.pojo.User;
import com.maven.model.query.QueryResource;
import com.maven.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleDao roleDao;

	/**
	 * 创建权限
	 * 
	 */
	public void createResource(Resource resource) {

		resourceDao.createResource(resource);
	}

	/**
	 * 更新权限信息
	 * 
	 */
	public boolean updateResource(Resource resource) {

		int count = resourceDao.updateResource(resource);
		if (count == 1) { // 更新成功
			return true;
		}
		return false;
	}

	/**
	 * 根据id删除权限
	 */
	public boolean deleteResource(int resourceId) {

		int state = resourceDao.deleteResource(resourceId);
		if (state == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 根据条件获取分页数据集合
	 * 
	 */
	public List<Resource> getAll(int limit, int page, String name, String type) {
		// 创建查询权限条件对象
		QueryResource qr = new QueryResource();
		qr.setLimit(limit);
		qr.setPage(page);
		qr.setName(name);
		qr.setType(type);
		// 根据条件获取查询到的权限集合
		List<Resource> list = resourceDao.getAll(qr);
		return list;
	}

	/**
	 * 根据条件查询数据总数
	 * 
	 * @param name
	 * @param type
	 * @return
	 */
	public int getAllCount(String name, String type) {
		// 创建查询权限条件对象
		QueryResource qr = new QueryResource();
		qr.setName(name);
		qr.setType(type);
		// 根据条件获取查询到的权限集合
		int total = resourceDao.getAllCount(qr);
		return total;
	}

	public Set<String> findResources(String username) {

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
					set.add(resourceDao.findResourceById(resourceId));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return set;
	}

	public List<Resource> findMenus(Set<String> resources) {

		return null;
	}

	public List<Resource> findResourceByName(Resource resource) {

		return resourceDao.findResourceByName(resource);
	}

	public int updateResourceState(Resource resource) {

		return resourceDao.updateResourceState(resource);
	}

	@Transactional
	public boolean deleteSelectedResource(String ids) {

		try {
			// 截取字符串获取id
			String[] string = ids.split(",");
			for (String str : string) {
				Integer id = Integer.valueOf(str); // 将String转换成Integer
				resourceDao.deleteResource(id); // 删除权限
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 手动回滚事务
		}
		return false;
	}

	/**
	 * 查询功能名是否存在
	 * 
	 * @param name
	 */
	public boolean queryNameIsExist(String name) {
		int state = resourceDao.queryNameIsExist(name); // 查询是否存在
		if (state == 0) { // 不存在
			return true;
		}
		return false;
	}

	/**
	 * 查询此url是否存在
	 * 
	 * @param url
	 */
	public boolean queryUrlIsExist(String url) {
		int queryUrlIsExist = resourceDao.queryUrlIsExist(url); // 查询是否存在
		if (queryUrlIsExist == 0) { // 不存在
			return true;
		}
		return false;
	}

	/**
	 * 查询此权限字符串是否存在
	 * 
	 * @param permission
	 * @return
	 */
	public boolean queryPermissionIsExist(String permission) {
		int queryPermissionIsExist = resourceDao.queryPermissionIsExist(permission); // 查询是否存在
		if (queryPermissionIsExist == 0) { // 不存在
			return true;
		}
		return false;
	}

	/**
	 * 根据功能类型获取功能列表
	 * 
	 * @param type
	 * @return
	 */
	public List<Resource> queryAllMenu(String type) {
		
		return resourceDao.queryAllMenu(type);
	}

	/**
	 * 根据id查询功能
	 * 
	 * @param id
	 * @return
	 */
	public Resource queryResourceById(int id) {
		
		return resourceDao.queryResourceById(id);
	}

}
