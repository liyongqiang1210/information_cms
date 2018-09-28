package com.maven.model.query;

import java.io.Serializable;

/**
 * <p>
 * Title: QueryResource
 * </p>
 * <p>
 * Description:权限查询实体类
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年9月28日 下午2:04:02
 */
public class QueryResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer limit;
	private Integer offset;
	private String resourceName;

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

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
