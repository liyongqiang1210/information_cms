package com.maven.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maven.model.pojo.Resource;
import com.maven.service.impl.ResourceServiceImpl;
import com.maven.util.MessageUtil;

/**
 * 资源控制层
 * 
 * @author Li Yongqiang
 *
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController {

	@Autowired
	private ResourceServiceImpl resourceServiceImpl;

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
	 * 查询全部资源信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/findAll.do", method = RequestMethod.GET)
	@ResponseBody
	public String findAll(HttpServletRequest request) {

		try {
			List<Resource> list = resourceServiceImpl.findAll();
			return MessageUtil.getJsonArrry(list);
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
	 * @param resource 资源对象
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

}
