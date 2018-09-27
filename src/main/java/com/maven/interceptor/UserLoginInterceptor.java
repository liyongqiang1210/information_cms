package com.maven.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * <p>
 * Title: UserLoginInterceptorBySpring
 * </p>
 * <p>
 * Description: 用户登录拦截器
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月19日 下午5:11:34
 */
public class UserLoginInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(UserLoginInterceptor.class);

	// 在业务处理器处理请求之前被调用
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.debug("****************用户开始登录****************");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String ip = getIp(request);
		String url = requestUri.substring(contextPath.length());
		log.debug("ip: " + ip);
		log.debug("requestUri: " + requestUri);
		log.debug("contextPath: " + contextPath);
		log.debug("url: " + url);

		return true;

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
		// 获取用户名
		String username = (String) request.getSession().getAttribute("username");
		log.debug("****************用户名：" + username + "登录成功****************");
		log.debug("****************开始生成视图****************");
	}

	// 在DispatcherServlet完全处理完请求之后被调用，可用于清理资源
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		log.debug("****************UserLoginInterceptor执行结束****************");
	}

}
