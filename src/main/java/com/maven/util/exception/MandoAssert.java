package com.maven.util.exception;

public class MandoAssert {

	public static void isTrue(boolean expression, IExceptionCode code, boolean needLogger) {
		isTrue(expression, code.getDescribe(), needLogger);
	}

	/**
	 * 条件真假判断，假抛出异常
	 * 
	 * @param expression
	 *            判断条件
	 * @param message
	 *            错误提示信息
	 * @param needLogger
	 *            该异常是否需要日志打印
	 */
	public static void isTrue(boolean expression, String message, boolean needLogger) {
		if (!expression) {
			if (needLogger)
				throw new MandoNeedLoggerException(message);
			else
				throw new MandoErrorMessageException(message);
		}
	}

	public static void isTrue(boolean expression, IExceptionCode code) {
		isTrue(expression, code.getDescribe());
	}

	/**
	 * 条件真假判断，假抛出异常
	 * 
	 * @param expression
	 *            判断条件
	 * @param message
	 *            错误提示信息
	 * 
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression)
			throw new MandoErrorMessageException(message);
	}

	public static void notTrue(boolean expression, IExceptionCode code, boolean needLogger) {
		notTrue(expression, code.getDescribe(), needLogger);
	}

	/**
	 * 条件真假判断，真抛出异常
	 * 
	 * @param expression
	 *            判断条件
	 * @param message
	 *            错误提示信息
	 * @param needLogger
	 *            该异常是否需要日志打印
	 * 
	 */
	public static void notTrue(boolean expression, String message, boolean needLogger) {
		if (expression) {
			if (needLogger)
				throw new MandoNeedLoggerException(message);
			else
				throw new MandoErrorMessageException(message);
		}
	}

	public static void notTrue(boolean expression, IExceptionCode code) {
		notTrue(expression, code.getDescribe());
	}

	/**
	 * 条件真假判断，真抛出异常
	 * 
	 * @param expression
	 *            判断条件
	 * @param message
	 *            错误提示信息
	 * 
	 */
	public static void notTrue(boolean expression, String message) {
		if (expression)
			throw new MandoErrorMessageException(message);
	}

	public static void notNull(Object object, IExceptionCode code, boolean needLogger) {
		notNull(object, code.getDescribe(), needLogger);
	}

	/**
	 * 条件空判断，错误抛出异常
	 * 
	 * @param object
	 *            判断对象
	 * @param message
	 *            错误提示信息
	 * @param needLogger
	 *            该异常是否需要日志打印
	 * 
	 */
	public static void notNull(Object object, String message, boolean needLogger) {
		if (object == null) {
			if (needLogger)
				throw new MandoNeedLoggerException(message);
			else
				throw new MandoErrorMessageException(message);
		}
	}

	public static void notNull(Object object, IExceptionCode code) {
		notNull(object, code.getDescribe());
	}

	/**
	 * 条件空判断，错误抛出异常
	 * 
	 * @param object
	 *            判断对象
	 * @param message
	 *            错误提示信息
	 * 
	 */
	public static void notNull(Object object, String message) {
		if (object == null)
			throw new MandoErrorMessageException(message);
	}

	public static void isNull(Object object, IExceptionCode code, boolean needLogger) {
		isNull(object, code.getDescribe(), needLogger);
	}

	/**
	 * 条件空判断，错误抛出异常
	 * 
	 * @param object
	 *            判断对象
	 * @param message
	 *            错误提示信息
	 * @param needLogger
	 *            该异常是否需要日志打印
	 * 
	 */
	public static void isNull(Object object, String message, boolean needLogger) {
		if (object != null) {
			if (needLogger)
				throw new MandoNeedLoggerException(message);
			else
				throw new MandoErrorMessageException(message);
		}
	}

	public static void isNull(Object object, IExceptionCode code) {
		isNull(object, code.getDescribe());
	}

	/**
	 * 条件空判断，错误抛出异常
	 * 
	 * @param object
	 *            判断对象
	 * @param message
	 *            错误提示信息
	 * 
	 */
	public static void isNull(Object object, String message) {
		if (object != null)
			throw new MandoErrorMessageException(message);
	}

}
