package com.maven.util.exception;


public class MandoNeedLoggerException extends RuntimeException implements IMandoException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public MandoNeedLoggerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MandoNeedLoggerException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MandoNeedLoggerException(Throwable cause) {
		super(cause);
	}

}
