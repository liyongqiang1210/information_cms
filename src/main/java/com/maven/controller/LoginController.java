package com.maven.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maven.util.CodeUtil;
import com.maven.util.json.JsonResult;

/**
 * 用户登录控制器
 * @author liyongqiang
 *
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param username 用户名
	 * @param password 密码
	 * @param checkCode 验证码
	 * @return 返回相应的状态信息
	 */
	@RequestMapping(value = "/toLogin.do")
	@ResponseBody
	public JsonResult toLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, boolean rememberMe,String checkCode) {
		
		// 用户名或者密码非空校验
		if(username == "" || username == null || password == "" || password == null) {
			return JsonResult.buildFailedResult("用户名或者密码不能为空!");
		}
		
		// 从session中读取验证码的实际值
		HttpSession session = request.getSession();
		String code = (String) session.getAttribute("code");
		
		// 将用户名保存到session
		session.setAttribute("username", username);

		// 将实际值与用户输入值作比较
		if (code.equalsIgnoreCase(checkCode)) {

			// 通过 SecurityUtils 得到 Subject，其会自动绑定到当前线程
			Subject subject = SecurityUtils.getSubject();
			// 然后获取身份验证的 Token，如用户名 / 密码
			UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);

			try {
				subject.login(token);
				return JsonResult.buildSuccessResult("success");
			} catch (IncorrectCredentialsException e) {
				return JsonResult.buildFailedResult("账号或密码错误");
			} catch (UnknownAccountException e) {
				return JsonResult.buildFailedResult("账号或密码错误");
			} catch (ExcessiveAttemptsException e) {
				return JsonResult.buildFailedResult("登录失败次数过多,账户锁定五分钟");
			} catch (LockedAccountException e) {
				return JsonResult.buildFailedResult("帐号已被锁定");
			} catch (DisabledAccountException e) {
				return JsonResult.buildFailedResult("帐号已被禁用");
			} catch (ExpiredCredentialsException e) {
				return JsonResult.buildFailedResult("帐号已过期");
			} catch (UnauthorizedException e) {
				return JsonResult.buildFailedResult("您没有得到相应的授权");
			} catch (AuthenticationException e) {// 其它异常
				return JsonResult.buildFailedResult("意外的错误");
			}
		} else if (checkCode == "" || checkCode.equals("") || checkCode == null) {
			return JsonResult.buildFailedResult("请输入验证码");
		}else{
			return JsonResult.buildFailedResult("验证码错误");
		}
	}

	/**
	 * 生成验证码方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getCheckCode.do", method = RequestMethod.GET, produces = "text/html;charset=utf-8;")
	public void getCheckCode(HttpServletRequest request, HttpServletResponse response) {

		// 存放验证码和验证码图片的map集合
		Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
		HttpSession session = request.getSession();
		session.setAttribute("code", codeMap.get("code").toString());

		// 禁止图像缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
		response.setContentType("image/jpeg");

		try {
			// 将图像输出到Servlet输出流上
			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpg", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
