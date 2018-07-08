/**
 * 
 */
package com.maven.util.json;

import java.util.List;

/**
 * 分页接口
 * 
 * @author zyf
 */
public interface IPagination<T> {

	/**
	 * 获取实体数据对象
	 * @return
	 */
	List<T> getData();

	/**
	 * 获取总记录数
	 * @return
	 */
	long getTotal();

	/**
	 * 获取当前页
	 * @return
	 */
	int getCurPage();
	
	/**
	 * 获取每页数量
	 * @return
	 */
	int getPageSize();

	/**
	 * 获取总页数
	 * @return
	 */
	int getTotalPage();

}
