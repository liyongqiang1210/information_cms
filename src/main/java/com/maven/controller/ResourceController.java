package com.maven.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping(value = "/toResource.do")
	public String toResource() {

		return "forward:/user/resource.html";
	}

	/**
	 * 创建功能
	 * 
	 * @param request
	 * @param resource
	 * @return
	 */
	@PostMapping(value = "/createResource.do")
	@ResponseBody
	public JsonResult createResource(HttpServletRequest request, Resource resource) {

		try {
			resourceServiceImpl.createResource(resource);
			return JsonResult.buildSuccessResult("添加成功");
		} catch (Exception e) {
			log.debug(e.getMessage());
			return JsonResult.buildFailedResult("添加失败");
		}

	}

	/**
	 * 更新资源
	 * 
	 * @param request
	 * @param resource
	 * @return
	 */
	@PostMapping(value = "/updateResource.do")
	@ResponseBody
	public String updateResource(HttpServletRequest request, Resource resource) {

		try {
			resourceServiceImpl.updateResource(resource);
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
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/deleteResource.do")
	@ResponseBody
	public JsonResult deleteResource(HttpServletRequest request, int id) {

		try {
			boolean state = resourceServiceImpl.deleteResource(id);
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
	@GetMapping(value = "/getAll.do")
	@ResponseBody
	public JsonResult getAll(HttpServletResponse response, int limit, int page, String name, String type) {

		try {
			List<Resource> list = resourceServiceImpl.getAll(limit, page, name, type);
			int total = resourceServiceImpl.getAllCount(name, type);
			return JsonResult.buildTableDataResult(0, true, "查询数据成功", total, list);
		} catch (Exception e) {
			log.debug(e.getMessage());
			return JsonResult.buildTableDataResult(0, false, "查询数据失败", 0, null);
		}

	}

	/**
	 * 删除选中选项
	 * 
	 * @param ids
	 *            要删除的id字符串
	 * @return
	 */
	@PostMapping(value = "deleteSelectedResource.do")
	@ResponseBody
	public JsonResult deleteSelectedResource(String ids) {

		try {
			boolean state = resourceServiceImpl.deleteSelectedResource(ids); // 删除选中
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
	@PostMapping(value = "/findResourceByName.do")
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
	@PostMapping(value = "/updateResourceState.do")
	@ResponseBody
	public JsonResult updateResourceState(Resource resource) {
		try {
			// 更新权限状态
			int state = resourceServiceImpl.updateResourceState(resource);
			if (state == 1) {
				return JsonResult.buildSuccessResult("状态更新成功");
			}
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		return JsonResult.buildFailedResult("状态更新失败");
	}

	/**
	 * 查询数据库是否存在此功能名
	 * 
	 * @param name
	 * @return
	 */
	@PostMapping(value = "/queryNameIsExist.do")
	@ResponseBody
	public JsonResult queryNameIsExist(String name) {
		try {
			boolean queryNameIsExist = resourceServiceImpl.queryNameIsExist(name); // 查询是否存在
			if (queryNameIsExist) { // 不存在
				return JsonResult.buildSuccessResult("此功能名不存在");
			}
			return JsonResult.buildFailedResult("此功能名已经存在");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("出现异常");
		}
	}

	/**
	 * 查询数据库是否存在此url
	 * 
	 * @param url
	 * @return
	 */
	@PostMapping(value = "/queryUrlIsExist.do")
	@ResponseBody
	public JsonResult queryUrlIsExist(String url) {
		try {
			boolean queryUrlIsExist = resourceServiceImpl.queryUrlIsExist(url); // 查询是否存在
			if (queryUrlIsExist) { // 不存在
				return JsonResult.buildSuccessResult("此url不存在");
			}
			return JsonResult.buildFailedResult("此url已经存在");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("出现异常");
		}
	}

	/**
	 * 查询数据库是否存在此条权限字符串
	 * 
	 * @param url
	 * @return
	 */
	@PostMapping(value = "/queryPermissionIsExist.do")
	@ResponseBody
	public JsonResult queryPermissionIsExist(String url) {
		try {
			// 查询是否存在
			boolean queryPermissionIsExist = resourceServiceImpl.queryPermissionIsExist(url);
			if (queryPermissionIsExist) { // 不存在
				return JsonResult.buildSuccessResult("此权限字符串不存在");
			}
			return JsonResult.buildFailedResult("此权限字符串已经存在");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("出现异常");
		}
	}

	/**
	 * 查询全部菜单
	 * 
	 * @param type
	 * @return
	 */
	@PostMapping(value = "/queryAllMenu.do")
	@ResponseBody
	public JsonResult queryAllMenu(String type) {
		List<Resource> queryAllMenu = null;
		try {
			queryAllMenu = resourceServiceImpl.queryAllMenu(type);
			return JsonResult.buildMessageAndDataResult("查询成功", true, queryAllMenu);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildMessageAndDataResult("出现异常", false, queryAllMenu);
		}
	}

}
