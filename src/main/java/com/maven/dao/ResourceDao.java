package com.maven.dao;

import java.util.List;

import com.maven.model.pojo.Resource;
import com.maven.model.query.QueryResource;

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

	public List<Resource> findAll(QueryResource qr);

	public List<Resource> findResourceByName(Resource resource);

	public void updateResourceState(Resource resource);
}
