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
	private Integer limit;
	private Integer offset;
	private String rolename;

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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
