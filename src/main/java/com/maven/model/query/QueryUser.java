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
		if(limit.equals("")){
			limit = 0;
		}
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		if(offset.equals("")){
			offset = 0;
		}
		this.offset = offset * limit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
