package com.maven.service;

import java.util.List;
import java.util.Set;

import com.maven.model.pojo.Resource;

public interface ResourceService {

	public void createPermission(Resource resource);

	public boolean updatePermission(Resource resource);

	public boolean deletePermission(int permissionId);

	public List<Resource> getAll(int limit, int offset, String name, String type);

	/**
	 * 得到资源对应的权限字符串
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);

	/**
	 * 根据用户权限得到菜单
	 * 
	 * @param permissions
	 * @return
	 */
	List<Resource> findMenus(Set<String> permissions);

	public List<Resource> findResourceByName(Resource resource);

	public int updatePermissionState(Resource resource);
}
