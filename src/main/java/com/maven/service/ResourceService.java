package com.maven.service;

import java.util.List;
import java.util.Set;

import com.maven.entity.Resource;

public interface ResourceService {

	public void createResource(Resource resource);

	public void updateResource(Resource resource);

	public void deleteResource(int resourceId);

	public List<Resource> findAll();

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
}
