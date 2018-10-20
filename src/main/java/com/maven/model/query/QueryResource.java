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
	private int limit;
	private int page;
	private String name;
	private String type;

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page > 0) {
			this.page = (page - 1) * limit;
		} else {
			this.page = page * limit;
		}
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
