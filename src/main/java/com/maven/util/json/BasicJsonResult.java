/**
 * 版   本 ：    1.0
 * 创建日期 ：    2014年11月24日
 * 作   者 ：    张云飞  
 */
package com.maven.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 单个对象的json结果
 * @author zyf
 *
 */
@JsonInclude(value=Include.NON_NULL)
public class BasicJsonResult extends JsonResult {
	
	
	protected BasicJsonResult() {
	}

	// Model对象
	private Object data;
	
	
	@SuppressWarnings("unchecked")
	public <E> E getData() {
		return (E) data;
	}

	public <E> void setData(E data) {
		this.data = data;
	}

	
	
}
