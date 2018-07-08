/**
 * 版   本 ：    1.0
 * 创建日期 ：    2014年11月24日
 * 作   者 ：    张云飞  
 */
package com.maven.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Collection;

/**
 * 
 * @author zyf
 *
 */
@JsonInclude(value=Include.NON_NULL)

public class CollectionJsonResult extends BasicJsonResult {

	// 数据列表
	private Collection<?> items;

	/**
	 * 数据列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> Collection<E> getItems() {
		return (Collection<E>) items;
	}

	/**
	 * 数据列表
	 * 
	 * @param items
	 */
	public <E> void setItems(Collection<E> items) {
		this.items = items;
	}


	
	
}
