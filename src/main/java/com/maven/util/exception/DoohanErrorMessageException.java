package com.maven.util.exception;

/**
 * 
 * 
 * */
public class DoohanErrorMessageException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public DoohanErrorMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DoohanErrorMessageException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DoohanErrorMessageException(Throwable cause) {
		super(cause);
	}

}
