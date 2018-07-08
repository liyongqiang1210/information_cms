package com.maven.util.exception;

public class DoohanNeedLoggerException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public DoohanNeedLoggerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DoohanNeedLoggerException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DoohanNeedLoggerException(Throwable cause) {
		super(cause);
	}

}
