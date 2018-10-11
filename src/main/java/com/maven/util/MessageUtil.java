package com.maven.util;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maven.model.pojo.Role;

/**
 * 服务器返回信息工具类 格式都是json字符串格式
 * 
 * @author Li Yongqiang
 *
 */
public class MessageUtil {

	public static final String SUCCESS_MESSAGE = "{\"msg\":\"success\"}"; // 请求成功时返回的状态信息
	public static final String ERROR_MESSAGE = "{\"msg\":\"error\"}"; // 请求失败返回的状态信息

	public static String getLayuiJson(Integer total, List<Role> list) {
		return layuiJson(total, list);
	}

	/**
	 * layui所需的json格式数据
	 * 
	 * @param total
	 * @param rows
	 * @return
	 */
	private static String layuiJson(Integer total, List<Role> list) {
		JSONObject json = new JSONObject();
		json.put("code", 0);
		json.put("msg", "成功");
		json.put("count", total);
		json.put("data", list);
		return json.toJSONString();
	}

	/**
	 * 获取json字符串
	 * 
	 * @param o
	 *            传入的对象值
	 * @return
	 */
	public static String getJsonArrry(Object o) {

		return JSONArray.toJSONString(o);
	}

	public static String getJsonArrry(int total, String rows) {

		String jsonStr = "{\"total\":" + total + ",\"rows\":" + rows + "}";

		return jsonStr;

	}

	/**
	 * 获取想要返回的json字符串信息
	 * 
	 * @param str
	 *            想要返回的信息内容
	 * @return
	 */
	public static String getStateInfo(String str) {

		return "{\"msg\":\"" + str + "\"}";
	}

}
