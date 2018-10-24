package com.maven.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.maven.model.pojo.Role;
import com.maven.service.impl.RoleServiceImpl;
import com.maven.util.MessageUtil;
import com.maven.util.JsonResult;

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
	@GetMapping(value = "/toRole.do")
	public String toRole() {
		return "forward:/role/role.html";
	}

	/**
	 * 获取所有角色信息列表
	 * 
	 * @return
	 */
	@GetMapping(value = "/getAllRole.do")
	@ResponseBody
	public String getAllRole(HttpServletRequest request, HttpServletResponse response, Integer limit, Integer offset,
			String rolename, int available) {
		// 解决跨域问题，这里需要设置头信息，不然客户端无法接收到返回值
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

		if (limit == null || offset == null || rolename == null) {
			return MessageUtil.getStateInfo("参数中存在空值");
		}
		// 获取角色列表
		List<Role> list = roleServiceImpl.findAll(limit, offset, rolename, available);
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
	@PostMapping(value = "/createRole.do")
	@ResponseBody
	public JsonResult createRole(String roleName, String roleDesc, int available) {

		try {
			roleServiceImpl.createRole(new Role(roleName, roleDesc, "", available));
			return JsonResult.buildSuccessResult("角色添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("角色添加失败");
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/deleteRole.do")
	@ResponseBody
	public JsonResult deleteRole(int roleId) {
		try {
			roleServiceImpl.deleteRole(roleId);
			return JsonResult.buildSuccessResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("failed");
		}
	}

	/**
	 * 更新角色
	 * 
	 * @param roleId
	 * @param roleName
	 * @param roleDesc
	 * @return
	 */
	@PostMapping(value = "/updateRole.do")
	@ResponseBody
	public JsonResult updateRole(Integer roleId, String roleName, String roleDesc) {

		try {
			Role role = new Role(roleId, roleName, roleDesc);
			roleServiceImpl.updateRole(role);
			return JsonResult.buildSuccessResult("角色更新成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("角色更新失败");
		}

	}

	/**
	 * 删除选中的角色
	 * 
	 * @param request
	 * @param ids
	 */
	@PostMapping(value = "deleteSelectedRole.do")
	@ResponseBody
	public JsonResult deleteSelectedRole(String ids) {

		try {
			roleServiceImpl.deleteSelectedRole(ids);
			return JsonResult.buildSuccessResult("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("删除失败");
		}
	}

	/**
	 * 根据角色名查询角色名是否存在
	 * 
	 * @param rolename
	 * @return
	 */
	@PostMapping(value = "/queryRoleNameIsExist.do")
	@ResponseBody
	public JsonResult queryRoleNameIsExist(String roleName) {

		try {
			// 获取角色名是否存在
			int count = roleServiceImpl.queryRoleNameIsExist(roleName);
			if (count > 0) {
				return JsonResult.buildFailedResult("角色名已经存在");
			}
			return JsonResult.buildSuccessResult("角色名可用");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildFailedResult("出现异常");
		}
	}

	/**
	 * 根据角色id更新角色状态
	 * 
	 * @param role
	 * @return
	 */
	@PostMapping(value = "/updateRoleState.do")
	@ResponseBody
	public JsonResult updateRoleState(Role role) {
		try {
			roleServiceImpl.updateRoleState(role);
			return JsonResult.buildSuccessResult("success");
		} catch (Exception e) {
			return JsonResult.buildFailedResult("failed");
		}
	}

	/**
	 * 获取全部数据
	 * 
	 * @param response
	 * @param limit
	 * @param offset
	 * @param rolename
	 * @return
	 */
	@GetMapping(value = "/getAll.do")
	@ResponseBody
	public JsonResult getAll(HttpServletResponse response, int limit, int page, String roleName, Integer available) {
		// 解决跨域问题，这里需要设置头信息，不然客户端无法接收到返回值
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		// 如果available为null时给它赋默认值2
		if (available == null) {
			available = 2;
		}
		try {
			// 获取角色列表
			List<Role> list = roleServiceImpl.findAll(limit, page, roleName, available);
			// 查询角色总数
			int total = roleServiceImpl.queryRoleCount(roleName, available);
			return JsonResult.buildTableDataResult(0, true, "查询成功", total, list);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.buildTableDataResult(0, true, "查询失败", 0, null);
		}
	}

}
