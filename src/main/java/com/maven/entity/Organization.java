package com.maven.entity;

import java.io.Serializable;

public class Organization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; // 组织机构id
	private String name; // 组织机构名称
	private int priority; // 显示顺序
	private int parentId; //父编号
	private String parentIds; //父编号列表
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
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
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
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public Organization(int id, String name, int priority, int parentId, String parentIds, int available) {
		super();
		this.id = id;
		this.name = name;
		this.priority = priority;
		this.parentId = parentId;
		this.parentIds = parentIds;
		this.available = available;
	}
	public Organization() {
		super();
	}
	@Override
	public String toString() {
		return "Organization {id=" + id + ", name=" + name + ", priority=" + priority + ", parentId=" + parentId
				+ ", parentIds=" + parentIds + ", available=" + available + "}";
	}
	
	

}
