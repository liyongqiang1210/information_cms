package com.maven.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maven.model.pojo.Resource;
import com.maven.service.impl.ResourceServiceImpl;
import com.maven.util.JsonResult;
import com.maven.util.MessageUtil;

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
@Controller
// @RequestMapping(value = "/resource")
@RequestMapping(value = "/permission")
public class ResourceController {

	@Autowired
	private ResourceServiceImpl resourceServiceImpl;

	private static final Logger log = LoggerFactory.getLogger(ResourceController.class);

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
	@PostMapping(value = "/createPermission.do")
	@ResponseBody
	public String createPermission(HttpServletRequest request, Resource resource) {

		try {
			resourceServiceImpl.createPermission(resource);
			return MessageUtil.SUCCESS_MESSAGE;
		} catch (Exception e) {
			log.debug(e.getMessage());
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
	@PostMapping(value = "/updatePermission.do")
	@ResponseBody
	public String updatePermission(HttpServletRequest request, Resource resource) {

		try {
			resourceServiceImpl.updatePermission(resource);
			return MessageUtil.SUCCESS_MESSAGE;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return MessageUtil.ERROR_MESSAGE;
		}
	}

	/**
	 * 根据id删除权限
	 * 
	 * @param request
	 * @param permissionId
	 * @return
	 */
	@PostMapping(value = "/deletePermission.do")
	@ResponseBody
	public JsonResult deletePermission(HttpServletRequest request, int permissionId) {

		try {
			boolean state = resourceServiceImpl.deletePermission(permissionId);
			if (state) {
				return JsonResult.buildSuccessResult("删除成功");
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return JsonResult.buildFailedResult("删除失败");
	}

	/**
	 * 根据条件查询权限
	 * 
	 * @param request
	 * @param response
	 * @param limit
	 * @param offset
	 * @param name
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/getAll.do", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getAll(HttpServletResponse response, int limit, int page, String name, String type) {

		try {
			List<Resource> list = resourceServiceImpl.getAll(limit, page, name, type);
			int total = resourceServiceImpl.getAllCount(name, type);
			return JsonResult.buildSuccessLayuiResult(0, "success", total, list);
		} catch (Exception e) {
			e.getMessage();
			return JsonResult.buildFailedLayuiResult(-1, "error");
		}

	}

	/**
	 * 删除选中选项
	 * 
	 * @param ids
	 *            要删除的id字符串
	 * @return
	 */
	@PostMapping(value = "deleteSelectedPermission.do")
	@ResponseBody
	public JsonResult deleteSelectedPermission(String ids) {

		try {
			boolean state = resourceServiceImpl.deleteSelectedPermission(ids); // 删除选中
			if (state) {
				return JsonResult.buildSuccessResult("删除成功");
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return JsonResult.buildFailedResult("删除失败");
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
			log.debug(e.getMessage());
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
	@RequestMapping(value = "/updatePermissionState.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updatePermissionState(Resource resource) {
		try {
			// 更新权限状态
			int state = resourceServiceImpl.updatePermissionState(resource);
			if (state == 1) {
				return JsonResult.buildSuccessResult("状态更新成功");
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return JsonResult.buildFailedResult("状态更新失败");
	}

}
