/**
 * 版   本 ：    1.0
 * 创建日期 ：    2014年11月24日
 * 作   者 ：    张云飞  
 */
package com.maven.util.exception;

/**
 * @author zyf
 *
 */
public class SimpleException extends AbstractBizException {


	private static final long serialVersionUID = 1L;

	public SimpleException(String message) {
		super(message);
	}

	public SimpleException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SimpleException(Throwable cause) {
		super(cause);
	}
	
}
