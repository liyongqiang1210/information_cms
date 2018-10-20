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
	
	public void createPermission(Resource resource);

	public int updatePermission(Resource resource);

	public int deletePermission(int resourceId);

	public String findPermissionById(int resourceId);

	public List<Resource> getAll(QueryResource qr);

	public List<Resource> findResourceByName(Resource resource);

	public int updatePermissionState(Resource resource);

	public int getAllCount(QueryResource qr);
}
