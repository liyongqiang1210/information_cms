package com.maven.util.exception;


/**
 * 
 * 
 * */
public class MandoErrorMessageException extends RuntimeException implements IMandoException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public MandoErrorMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public MandoErrorMessageException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MandoErrorMessageException(Throwable cause) {
		super(cause);
	}

}
