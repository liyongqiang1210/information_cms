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
import com.alibaba.fastjson.JSONObject;
import com.maven.model.pojo.Role;
import com.maven.service.impl.RoleServiceImpl;
import com.maven.util.MessageUtil;
import com.maven.util.json.JsonResult;

/**
 * <p>
 * Title: RoleController
 * </p>
 * <p>
 * Description: 角色控制器
 * </p>
 *
 * @author Li Yongqiang
 * @datetime 2018年9月27日 下午9:38:17
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	/**
	 * 跳转到角色页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toRole.do", method = RequestMethod.GET)
	public String toRole() {
		return "forward:/user/role.html";
	}

	/**
	 * 获取所有角色信息列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllRole.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getAllRole(HttpServletRequest request, HttpServletResponse response, Integer limit, Integer offset,
			String rolename) {
		// 解决跨域问题，这里需要设置头信息，不然客户端无法接收到返回值
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

		if (limit == null || offset == null || rolename == null) {
			return MessageUtil.getStateInfo("参数中存在空值");
		}
		// 获取角色列表
		List<Role> list = roleServiceImpl.findAll(limit, offset, rolename);
		int total = list.size();
		return MessageUtil.getJsonArrry(total, JSONArray.toJSONString(list));
	}

	/**
	 * 创建角色
	 * 
	 * @param roleName
	 *            ： 角色名
	 * @param roleDesc
	 *            ： 角色描述
	 * @param resourceIds
	 *            ： 角色资源id
	 * @return
	 */
	@RequestMapping(value = "/createRole.do", method = RequestMethod.POST)
	@ResponseBody
	public String createRole(HttpServletRequest request, String roleName, String roleDesc, String resourceIds) {

		JSONObject jsonObject = new JSONObject();

		Role role = new Role(roleName, roleDesc, resourceIds);

		try {

			roleServiceImpl.createRole(role);
			jsonObject.put("msg", "success");

		} catch (Exception e) {

			jsonObject.put("msg", "error");
			e.printStackTrace();
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteRole.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteRole(HttpServletRequest request, int roleId) {
		JSONObject jsonObject = new JSONObject();
		try {

			roleServiceImpl.deleteRole(roleId);
			jsonObject.put("msg", "success");
		} catch (Exception e) {

			jsonObject.put("msg", "error");
			e.printStackTrace();
		}

		return jsonObject.toJSONString();
	}

	/**
	 * 更新角色
	 * 
	 * @param request
	 * @param roleId
	 * @param roleName
	 * @param roleDesc
	 * @param resourceIds
	 * @param available
	 * @return
	 */
	@RequestMapping(value = "/updateRole.do", produces = "text/html;charset=UTF-8;", method = RequestMethod.POST)
	@ResponseBody
	public String updateRole(HttpServletRequest request, Integer roleId, String roleName, String roleDesc,
			String resourceIds, Integer available) {

		try {
			Role role = new Role(roleId, roleName, roleDesc, resourceIds, available);
			roleServiceImpl.updateRole(role);

			return "{'msg':'success'}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{'msg':'error'}";
		}

	}

	/**
	 * 删除选中的角色
	 * 
	 * @param request
	 * @param ids
	 */
	@RequestMapping(value = "deleteSelectedRole.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteSelectedRole(HttpServletRequest request, String ids) {

		try {
			if (ids != null && !ids.equals("")) {
				String[] split = ids.split(",");
				for (String str : split) {
					Integer roleId = Integer.valueOf(str);
					roleServiceImpl.deleteRole(roleId);
				}
			}
			return "{'msg':'success'}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{'msg':'error'}";
		}
	}

	@RequestMapping(value = "/findRoleNameByUserName.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String findRoleNameByUserName(String username) {

		String rolename = "";
		if (username != null && username != "") {
			rolename = roleServiceImpl.findRoleNameByUserName(username);
		}

		return "{\"rolename\":\"" + rolename + "\"}";
	}

	/**
	 * 根据角色id更新角色状态
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(value = "/updateRoleState.do", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateRoleState(Role role) {
		try {
			roleServiceImpl.updateRoleState(role);
		} catch (Exception e) {
			return JsonResult.buildFailedResult("failed");
		}
		return JsonResult.buildSuccessResult("success");
	}

	@RequestMapping(value = "/getAll.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String getAll(HttpServletResponse response, Integer limit, Integer offset, String rolename) {
		// 解决跨域问题，这里需要设置头信息，不然客户端无法接收到返回值
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

		if (limit == null || offset == null || rolename == null) {
			return MessageUtil.getStateInfo("参数中存在空值");
		}
		// 获取角色列表
		List<Role> list = roleServiceImpl.findAll(limit, offset, rolename);
		List<Role> findAll = roleServiceImpl.findAll(30, 1, "");
		int total = findAll.size();
		return MessageUtil.getLayuiJson(total, list);
	}
}
