package com.maven.entity;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private String salt;
	private String phone;
	private String email;
	private int sex;
	private String roleIds;
	private String remarks;
	private String locked;
	private String createTime;

	// 角色名字
	private String roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getCredentialsSalt() {
		return username + salt;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public User(int id, String username, String password, String salt, String phone, String email, String roleName,
			String locked, String createTime, String remarks, int sex) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.phone = phone;
		this.email = email;
		this.roleName = roleName;
		this.locked = locked;
		this.createTime = createTime;
		this.remarks = remarks;
		this.sex = sex;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", phone="
				+ phone + ", email=" + email + ", sex=" + sex + ", roleIds=" + roleIds + ", remarks=" + remarks
				+ ", locked=" + locked + ", createTime=" + createTime + ", roleName=" + roleName + "]";
	}

}
