package com.maven.util.json;

/**
 * 能够还回失败编码及自定义model设置的 json返回结果
 * 
 * @author xlf
 *
 */
class SimpleJsonResult extends JsonResult {

	// 提示编号
	private String code;

	public SimpleJsonResult() {
		super();
	}



	public static SimpleJsonResult build() {
		SimpleJsonResult result = new SimpleJsonResult();
		return result;
	}



	/**
	 * 是否成功
	 * 
	 * @param success
	 */
	public SimpleJsonResult setSuccess(Boolean success) {
		super.setSuccess(success);
		return this;
	}

	public String getCode() {
		return code;
	}

	void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设置成功或失败信息
	 * 
	 * @param message
	 */
	public SimpleJsonResult setMessage(String message) {
		super.setMessage(message);
		return this;
	}

}
