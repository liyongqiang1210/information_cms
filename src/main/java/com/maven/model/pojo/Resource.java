package com.maven.model.pojo;

import java.io.Serializable;

public class Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; // 资源id
	private String name; // 资源名称
	private String type; // 资源类型 按钮或菜单
	private String url; // 资源url
	private int parentId; // 父编号
	private String parentIds; // 父编号列表
	private String permission; // 权限字符串
	private int available; // 是否可用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Resource(int id, String name, String type, String url, int parentId, String parentIds, String permission,
			int available) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.url = url;
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.permission = permission;
		this.available = available;
	}

	public Resource() {
		super();
	}

	@Override
	public String toString() {
		return "Resource {id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", parentId=" + parentId
				+ ", parentIds=" + parentIds + ", permission=" + permission + ", available=" + available + "}";
	}

}
