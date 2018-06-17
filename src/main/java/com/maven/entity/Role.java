package com.maven.entity;

import java.io.Serializable;

public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; // 角色id
	private String roleName; // 角色名称
	private String roleDesc; // 角色描述
	private String resourceIds; // 资源id
	private int available; // 角色是否可用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Role() {
		super();
	}

	public Role(int id, String roleName, String roleDesc, String resourceIds, int available) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.resourceIds = resourceIds;
		this.available = available;
	}

	public Role(String roleName, String roleDesc, String resourceIds) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.resourceIds = resourceIds;
	}

	public Role(String roleName, String roleDesc, String resourceIds, int available) {
		super();
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.resourceIds = resourceIds;
		this.available = available;
	}

	@Override
	public String toString() {
		return "Role {id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", resourceIds=" + resourceIds
				+ ", available=" + available + "}";
	}

}
