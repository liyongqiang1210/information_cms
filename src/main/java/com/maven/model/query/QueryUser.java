package com.maven.model.query;

import java.io.Serializable;

/**
 * 用戶查询实体类
 * 
 * @author Li Yongqiang
 *
 */
public class QueryUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer limit;
	private Integer offset;
	private String username;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset * limit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
