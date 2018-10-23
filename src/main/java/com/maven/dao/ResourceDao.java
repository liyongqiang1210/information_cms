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

	public int updateResource(Resource resource);

	public int deleteResource(int id);

	public String findResourceById(int id);

	public List<Resource> getAll(QueryResource qr);

	public List<Resource> findResourceByName(Resource resource);

	public int updateResourceState(Resource resource);

	public int getAllCount(QueryResource qr);

	public int queryNameIsExist(String name);

	public int queryUrlIsExist(String url);
	
	public int queryPermissionIsExist(String permission);

	public List<Resource> queryAllMenu(String type);

	public Resource queryResourceById(int id);
}
