package com.maven.service;

import java.util.List;
import java.util.Set;

import com.maven.model.pojo.Resource;

public interface ResourceService {

	public void createResource(Resource resource);

	public boolean updateResource(Resource resource);

	public boolean deleteResource(int id);

	public List<Resource> getAll(int limit, int offset, String name, String type);

	/**
	 * 得到资源对应的权限字符串
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findResources(String username);

	/**
	 * 根据用户权限得到菜单
	 * 
	 * @param resources
	 * @return
	 */
	List<Resource> findMenus(Set<String> resources);

	public List<Resource> findResourceByName(Resource resource);

	public int updateResourceState(Resource resource);
}
