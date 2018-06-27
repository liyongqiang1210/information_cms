package com.maven.service;

import java.util.List;

import com.maven.model.pojo.Organization;

/**
 * 
 * @author liyongqiang
 *
 */
public interface OrganizationService {

	public Organization createOrganization(Organization organization);

	public Organization updateOrganization(Organization organization);

	public void deleteOrganization(int organizationId);

	Organization findOne(int organizationId);

	List<Organization> findAll();

	Object findAllWithExclude(Organization excludeOraganization);

	void move(Organization source, Organization target);
}
