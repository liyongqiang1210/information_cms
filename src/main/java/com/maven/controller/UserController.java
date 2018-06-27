package com.maven.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.maven.model.pojo.User;
import com.maven.model.query.QueryUser;
import com.maven.service.impl.UserServiceImpl;
import com.maven.util.DateUtil;
import com.maven.util.MessageUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/getUserInfo")
	public String getUserInfo(HttpServletRequest request) {
		String currentUser = (String) request.getSession().getAttribute("currentUser");
		System.out.println("当前登录的用户为[" + currentUser + "]");
		request.setAttribute("currUser", currentUser);
		return "/user/info";
	}

	@RequestMapping(value = "getUserByUserName")
	@ResponseBody
	public String getUserByUsername(HttpServletRequest request, String userName) {
		JSONArray jsonArray = new JSONArray();
		User user = userServiceImpl.findByUsername(userName);
		jsonArray.add(user);
		return jsonArray.toJSONString();
	}

	/**
	 * 
	 * @param limit
	 *            页面大小
	 * @param offset
	 *            页码
	 * @param userName
	 * @param createTime
	 * @return
	 */
	@RequestMapping(value = "/findAll.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findAll(Integer limit, Integer offset, String username) {

		try {
			if (limit == null || offset == null || username == null) {
				return MessageUtil.getStateInfo("参数中不能存在null");
			}
			QueryUser qu = new QueryUser();
			qu.setLimit(limit);
			qu.setOffset(offset);
			qu.setUsername(username);
			List<User> list = userServiceImpl.findAll(qu);
			int total = list.size();
			return MessageUtil.getJsonArrry(total, JSONArray.toJSONString(list));
		} catch (Exception e) {
			
			e.printStackTrace();
			return MessageUtil.ERROR_MESSAGE;
		}
	}

	/**
	 * 创建新用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/createUser.do", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(User user) {
		String msg = "";
		try {
			user.setCreateTime(DateUtil.getDateTimeString(new Date()));
			msg = userServiceImpl.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			msg = MessageUtil.ERROR_MESSAGE;
		}
		return msg;
	}

	/**
	 * 更新用户状态
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateUserState.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserState(User user) {

		String msg = "";
		try {
			msg = userServiceImpl.updateUserState(user);
		} catch (Exception e) {
			e.printStackTrace();
			msg = MessageUtil.ERROR_MESSAGE;
		}

		return msg;
	}

	/**
	 * 根据用户id删除单个用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteUser.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(int id) {
		String msg = "";
		try {
			msg = userServiceImpl.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg = MessageUtil.ERROR_MESSAGE;
		}
		return msg;
	}

	/**
	 * 根据用户id字符串删除多个用户
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteUsers.do", method = RequestMethod.POST)
	@ResponseBody
	public String deleteUser(String ids) {
		String msg = "";
		try {
			msg = userServiceImpl.deleteUsers(ids);
		} catch (Exception e) {
			e.printStackTrace();
			msg = MessageUtil.ERROR_MESSAGE;
		}
		return msg;
	}

}
