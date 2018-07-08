/**
 * 描   述: 实现与UI进行交互的JSON格式对象
 * 修改人: 韩飞虎
 * 修改时间: 2013年9月23日
 * 修改内容: 新建
 */
package com.maven.util.json;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maven.util.exception.ExceptionUtils;
import com.maven.util.exception.IExceptionCode;
import com.maven.util.exception.SimpleException;

/**
 * <p>
 * 定义与UI进行交互的统一JSON格式对象
 * </p>
 * <p>
 * 示例:
 * </p>
 * <p>
 * 
 * <pre>
 * JsonResult result = JsonResult.buildSuccessResult(list);
 * JsonResult result = JsonResult.buildFailedResult(&quot;无效的参数&quot;);
 * </pre>
 *
 * @author 韩飞虎
 * @see
 */
@JsonInclude(value = Include.NON_NULL)
public class JsonResult {

	private boolean success;

	private String message;

	// Model对象
	private Map<String, Object> model;

	/**
	 * 使用静态方法进行构造
	 *
	 * @author
	 * @date 2013年10月10日 下午3:40:58
	 */
	protected JsonResult() {

	}

	/**
	 * 构建成功的交互对象, 数据为单个结果对象
	 *
	 * @return JSON封装对象
	 */
	public static <E> JsonResult buildSuccessResult(E data) {

		BasicJsonResult result = new BasicJsonResult();

		if (data != null) {
			result.setSuccess(true);
			result.setMessage("OK");
			result.setData(data);
		} else {
			result.setSuccess(false);
			result.setMessage("数据为空");
		}
		return result;
	}

	public static <E> JsonResult buildSuccessResult(String message) {

		BasicJsonResult result = new BasicJsonResult();
		result.setSuccess(true);
		result.setMessage(message);

		return result;
	}

	public static <E> JsonResult buildSuccessMessageAndData(E data, String message) {

		BasicJsonResult result = new BasicJsonResult();
		result.setSuccess(true);
		result.setMessage(message);
		if (data != null) {
			result.setData(data);
		}

		return result;
	}

	/**
	 * 构建成功的交互对象, 不分页
	 *
	 * @param items
	 *            列表数据
	 * @return JSON封装对象
	 */
	public static <E> CollectionJsonResult buildSuccessResult(final List<E> items) {
		List<E> tmpdata = items;
		if (items == null) {
			tmpdata = new LinkedList<E>();
		}
		CollectionJsonResult result = new CollectionJsonResult();
		result.setSuccess(true);
		result.setMessage("OK");
		result.setItems(tmpdata);
		return result;
	}

	/**
	 * 构建成功的交互对象
	 *
	 * @param data
	 *            数据对象
	 * @param items
	 *            集合数据对象
	 * @return
	 */
	public static <E> CollectionJsonResult buildSuccessResult(E data, final List<E> items) {
		List<E> tmpdata = items;
		if (items == null) {
			tmpdata = new LinkedList<E>();
		}
		CollectionJsonResult result = new CollectionJsonResult();
		result.setSuccess(true);
		result.setItems(tmpdata);
		result.setData(data);
		return result;
	}

	/**
	 * 构建成功的交互对象
	 *
	 * @param data
	 *            分页数据对象
	 * @return JSON封装对象
	 */
	public static <E> PaginationJsonResult buildSuccessResult(IPagination<E> data) {
		PaginationJsonResult result = new PaginationJsonResult();
		result.setSuccess(true);
		result.setItems(data.getData());
		result.setTotal(data.getTotal());
		result.setPage(data.getCurPage());
		result.setPageSize(data.getPageSize());
		result.setTotalPage(data.getTotalPage());
		return result;
	}

	/**
	 * 构建成功的交互对象
	 *
	 * @param data
	 *            返回数据对象
	 * @param page
	 *            可以返回一个分页数据对象
	 * @return
	 */
	public static <E> PaginationJsonResult buildSuccessResult(E data, IPagination<E> page) {
		PaginationJsonResult result = new PaginationJsonResult();
		result.setSuccess(true);
		result.setItems(page.getData());
		result.setTotal(page.getTotal());
		result.setPage(page.getCurPage());
		result.setPageSize(page.getPageSize());
		result.setTotalPage(page.getTotalPage());
		result.setData(data);
		return result;
	}

	/**
	 * 构建交互对象， 可以指定成功或失败
	 *
	 * @param success
	 *            是否成功
	 * @param message
	 *            成功或失败信息
	 * @return
	 */
	public static JsonResult buildResult(boolean success, String message) {
		JsonResult result = new JsonResult();
		result.setSuccess(success);
		result.setMessage(message);
		return result;
	}

	public static JsonResult buildSuccessAndFail(boolean success) {
		JsonResult result = new JsonResult();
		if (success) {
			result.setSuccess(success);
			result.setMessage("操作成功");
		} else {
			result.setSuccess(success);
			result.setMessage("操作失败");
		}

		return result;
	}

	/**
	 * 构建失败的交互对象
	 *
	 * @param message
	 *            失败信息
	 * @return JSON封装对象
	 */
	public static <E> JsonResult buildFailedResult(String message) {

		JsonResult result = new JsonResult();
		result.setSuccess(false);
		result.setMessage(message);
		return result;

	}

	/**
	 * 构建对象
	 *
	 * @param code
	 * @return
	 */
	public static JsonResult buildFailedResult(IExceptionCode code) {
		SimpleJsonResult result = new SimpleJsonResult();
		if (code.getCode().equals("0"))
			result.setSuccess(true);
		else
			result.setSuccess(false);
		result.setMessage(code.getDescribe());
		result.setCode(code.getCode());
		return result;
	}

	/**
	 * 按异常信息构建返回对象
	 *
	 * @param e
	 * @param defaultMessage
	 * @return
	 */
	public static JsonResult buildExceptionResult(Throwable e, String defaultMessage) {
		e.printStackTrace();
		if (ExceptionUtils.isBizException(e) || ExceptionUtils.isAssertException(e)) {
			return buildFailedResult(e.getMessage());
		} else {
			return buildFailedResult(defaultMessage);
		}

	}

	/**
	 * @return the success
	 */
	public boolean getSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 * @return
	 */
	public JsonResult setMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * 返回JSON格式的字符串
	 */
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		String value = null;
		try {
			value = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new SimpleException("序列化为json异常", e);
		}

		return value;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public JsonResult setModel(String key, Object value) {
		if (model == null)
			model = new HashMap<String, Object>();

		model.put(key, value);
		return this;
	}

}
