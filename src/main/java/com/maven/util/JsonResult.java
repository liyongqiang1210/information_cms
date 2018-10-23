package com.maven.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 服务器返回数据统一处理类
 * 
 * @author Li Yongqiang
 *
 */
public class JsonResult {
	private Integer code; // 请求返回状态码
	private boolean success; // 请求是否成功
	private String message; // 返回信息
	private Object data; // 请求返回对象
	private Integer count; // 返回数据总数

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public JsonResult() {
		super();
	}

	public JsonResult(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}

	public JsonResult(int code, String message, Object data, int count) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.count = count;
	}

	@Override
	public String toString() {

		JSONObject json = new JSONObject();
		if (code != null) {
			json.put("code", code);
		}
		if (message != null) {
			json.put("message", message);
		}
		if (data != null) {
			json.put("data", data);
		}
		if (count != null) {
			json.put("count", count);
		}
		json.put("success", success);

		return json.toJSONString();
	}

	/**
	 * 返回带有success和message两个参数的JsonResult对象
	 * 
	 * @return
	 */
	public static JsonResult buildSuccessResult(String msg) {
		BasicJsonResult result = new BasicJsonResult();
		result.setSuccess(true);
		result.setMessage(msg);
		return result;
	}

	/**
	 * 返回带有success和message两个参数的JsonResult对象
	 * 
	 * @return
	 */
	public static JsonResult buildFailedResult(String msg) {
		BasicJsonResult result = new BasicJsonResult();
		result.setSuccess(false);
		result.setMessage(msg);
		return result;
	}

	/**
	 * layui请求成功时所需的json格式数据
	 * 
	 * @param total
	 * @param rows
	 * @return
	 */
	public static JsonResult buildSuccessLayuiResult(int code, String msg, int total,
			@SuppressWarnings("rawtypes") List list) {
		BasicJsonResult result = new BasicJsonResult();
		result.setSuccess(true);
		result.setData(list);
		result.setCode(code);
		result.setMessage(msg);
		result.setCount(total);
		return result;
	}

	/**
	 * layui请求失败时返回的json
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static JsonResult buildFailedLayuiResult(int code, String msg) {
		BasicJsonResult result = new BasicJsonResult();
		result.setSuccess(false);
		result.setData("");
		result.setCode(code);
		result.setMessage(msg);
		result.setCount(0);
		return result;
	}

	/**
	 * 
	 * @param message
	 *            信息
	 * @param success
	 *            本次请求的状态
	 * @param data
	 *            数据对象
	 * @return
	 */
	public static JsonResult buildMessageAndDataResult(String message, boolean success, Object data) {
		JsonResult result = new JsonResult();
		result.setSuccess(success);
		result.setData(data);
		result.setMessage(message);
		return result;
	}

}
