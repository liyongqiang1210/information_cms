package com.maven.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * <p>Title: UserLoginInterceptorBySpring</p>
 * <p>Description: 用户登录拦截器</p>
 * @author liyongqiang
 * @date 2018年9月19日 下午5:11:34
 */
public class UserLoginInterceptor implements HandlerInterceptor {

	// 在业务处理器处理请求之前被调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// equalsIgnoreCase 与 equals的区别？
		// if ("POST".equalsIgnoreCase(request.getMethod())) {
		// // RequestUtil.saveRequest();
		// }
		System.out.println("****************UserLoginInterceptor开始执行****************");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String ip = getIp(request);
		String url = requestUri.substring(contextPath.length());
		System.out.println("ip: " + ip);
		System.out.println("requestUri: " + requestUri);
		System.out.println("contextPath: " + contextPath);
		System.out.println("url: " + url);

		// 判断用户是否登录
		String username = (String) request.getSession().getAttribute("username");
		if (username != null && !username.equals("")) {
			System.out.println("****************用户名：" + username + "已登录****************");
			return true;
		} else {
			// 跳转到登录页面
			request.getRequestDispatcher("/login.html").forward(request, response);
			return false;
		}

	}

	/**
	 * 获取用户真实ip地址
	 * 
	 * @param request
	 */
	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 在业务处理器处理请求完成之后，生成视图之前执行
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("****************开始生成视图****************");
	}

	// 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		System.out.println("****************UserLoginInterceptor执行结束****************");
	}

}
