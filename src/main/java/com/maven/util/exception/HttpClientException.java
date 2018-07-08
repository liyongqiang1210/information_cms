
package com.maven.util.exception;

/**
 * 
 * @author 张云飞
 *
 */
public class HttpClientException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public HttpClientException() {

	}

	/**
	 * @param message
	 */
	public HttpClientException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HttpClientException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HttpClientException(String message, Throwable cause) {
		super(message, cause);

	}

}
