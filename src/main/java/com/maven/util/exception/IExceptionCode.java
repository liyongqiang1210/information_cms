/**
 * 版   本 ：    1.0
 * 创建日期 ：    2014年11月24日
 * 作   者 ：    张云飞  
 */
package com.maven.util.exception;

/**
 * 异常编码接口
 * 
 * @author hfh
 *
 */
public interface IExceptionCode {

	/**
	 * 获取异常编码
	 * 
	 * @return
	 */
	String getCode();

	/**
	 * 获取异常描述
	 * 
	 * @return
	 */
	String getDescribe();

}
