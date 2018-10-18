package com.maven.model.query;

import java.io.Serializable;

/**
 * <p>
 * Title: QueryRole
 * </p>
 * <p>
 * Description:角色查询实体类
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年9月28日 下午2:04:02
 */
public class QueryRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit;
	private int offset;
	private String roleName;
	private int available;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = (offset-1) * limit;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

}
