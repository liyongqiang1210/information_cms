package com.maven.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>
 * Title: LogoutController
 * </p>
 * <p>
 * Description: 用户退出控制器
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月27日 上午11:27:59
 */
@Controller
@RequestMapping(value = "/logout")
public class LogoutController {

	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String logout() {
		
		// 用户退出系统
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			subject.logout();
		}
		
		return "redirect:/login.html";
	}
}
