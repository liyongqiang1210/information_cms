package com.maven.service;

import java.util.List;
import java.util.Set;

import com.maven.model.pojo.Resource;

public interface ResourceService {

	public void createResource(Resource resource);

	public void updateResource(Resource resource);

	public void deleteResource(int resourceId);

	public List<Resource> findAll(Integer limit, Integer offset, String resourceName);

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

	public void updateResourceState(Resource resource);
}
