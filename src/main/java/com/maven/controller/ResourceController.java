package com.maven.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.maven.model.pojo.Resource;
import com.maven.service.impl.ResourceServiceImpl;
import com.maven.util.MessageUtil;
import com.maven.util.json.JsonResult;

/**
 * <p>
 * Title: ResourceController
 * </p>
 * <p>
 * Description:权限控制器
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年9月28日 下午3:22:48
 */
/**
 * <p>
 * Title: ResourceController
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年9月28日 下午3:26:42
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

	@Autowired
	private ResourceServiceImpl resourceServiceImpl;

	/**
	 * 跳转到权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toResource.do", method = RequestMethod.GET)
	public String toResource() {

		return "forward:/user/resource.html";
	}

	/**
	 * 创建资源
	 * 
	 * @param request
	 * @param resource
	 * @return
	 */
	@RequestMapping(value = "/createResource.do", method = RequestMethod.POST)
	@ResponseBody
	public String createResource(HttpServletRequest request, Resource resource) {

		try {
			resourceServiceImpl.createResource(resource);
			return MessageUtil.SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.ERROR_MESSAGE;
		}

	}

	/**
	 * 更新资源
	 * 
	 * @param request
	 * @param resource
	 * @return
	 */
	@RequestMapping(value = "/updateResource.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateResource(HttpServletRequest request, Resource resource) {

		try {
			resourceServiceImpl.updateResource(resource);
			return MessageUtil.SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.ERROR_MESSAGE;
		}
	}

	/**
	 * 根据id删除资源
	 * 
	 * @param request
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/deleteResource.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteResource(HttpServletRequest request, int resourceId) {

		try {
			resourceServiceImpl.deleteResource(resourceId);
			return MessageUtil.SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.ERROR_MESSAGE;
		}
	}

	/**
	 * 根据条件查询权限
	 * 
	 * @param request
	 * @param response
	 * @param limit
	 * @param offset
	 * @param resourceName
	 * @return
	 */
	@RequestMapping(value = "/findAll.do", method = RequestMethod.GET)
	@ResponseBody
	public String findAll(HttpServletRequest request, HttpServletResponse response, Integer limit, Integer offset,
			String resourceName) {

		try {
			List<Resource> list = resourceServiceImpl.findAll(limit, offset, resourceName);
			int total = list.size();
			return MessageUtil.getJsonArrry(total, JSONArray.toJSONString(list));
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.ERROR_MESSAGE;
		}
	}

	/**
	 * 删除选中选项
	 * 
	 * @param idStr
	 *            id字符串
	 * @return json字符串
	 */
	@RequestMapping(value = "deleteSelectedResource.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelectedResource(String idStr) {

		try {
			if (!idStr.equals("") && idStr != null) {
				String[] string = idStr.split(",");
				for (String str : string) {
					Integer id = Integer.valueOf(str);
					resourceServiceImpl.deleteResource(id);
				}
			}
			return MessageUtil.SUCCESS_MESSAGE;
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.ERROR_MESSAGE;
		}

	}

	/**
	 * 根据资源名搜索
	 * 
	 * @param resource
	 *            资源对象
	 * @return 返回json字符串
	 */
	@RequestMapping(value = "/findResourceByName.do", method = RequestMethod.POST)
	@ResponseBody
	public String findResourceByName(Resource resource) {
		try {
			List<Resource> list = resourceServiceImpl.findResourceByName(resource);
			return MessageUtil.getJsonArrry(list);
		} catch (Exception e) {
			e.printStackTrace();
			return MessageUtil.ERROR_MESSAGE;
		}

	}
	
	/**
	 * 根据id修改权限状态
	 * 
	 * @param id
	 * @param available
	 * @return
	 */
	@RequestMapping(value = "/updateResourceState.do", method = RequestMethod.POST)
	public JsonResult updateResourceState(Resource resource) {
		try {
			resourceServiceImpl.updateResourceState(resource);
			return JsonResult.buildSuccessResult("状态更新成功");
		} catch (Exception e) {
			return JsonResult.buildFailedResult("状态更新失败");
		}
	}

}
