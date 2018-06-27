package com.maven.dao;

import java.util.List;

import com.maven.model.pojo.Resource;

/**
 * 
 * @author liyongqiang
 *
 */
public interface ResourceDao {
	
	public void createResource(Resource resource);

	public void updateResource(Resource resource);

	public void deleteResource(int resourceId);

	public String findPermissionById(int resourceId);

	public List<Resource> findAll();

	public List<Resource> findResourceByName(Resource resource);
}
