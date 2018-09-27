package com.maven.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: AjaxFilter
 * </p>
 * <p>
 * Description: 用于shiro框架ajax请求拦截
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月25日 上午9:31:10
 */
public class AjaxFilter extends AdviceFilter {

	private static final Logger log = LoggerFactory.getLogger(AjaxFilter.class);

	/**
	 * 请求前处理
	 * 拦截到请求判断是否为ajax请求
	 */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 如果请求头中包含X-Requested-With参数那么这个请求就是ajax请求
		String requestType = req.getHeader("X-Requested-With");
		// 那么我们根据X-Requested-With参数的值来判断是否为ajax请求
		if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
			String requestURL = req.getRequestURI();// 获取到请求路径
			log.debug("拦截到ajax请求,请求地址为：" + requestURL);
			Subject subject = SecurityUtils.getSubject();// 获取到web容器管理的subject对象
			// 判断当前请求是否已经认证
			boolean isAuthc = subject.isAuthenticated();
			if (!isAuthc) {
				log.debug("当前账户使用shiro认证失败!");
				// 当前账户认证失败，本次请求被驳回,ajax请求重定向到登录页面
				return false;
			}
			return true;
		}
		// 默认返回的是true，将本次请求继续执行下去
		return super.preHandle(request, response);
	}

	/**
	 * 请求后处理
	 * 
	 */
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		super.postHandle(request, response);
	}
	
	/**
	 * 最终处理方法,一定会执行的方法,一般用于释放资源
	 */
	public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception)
			throws Exception {
		super.afterCompletion(request, response, exception);
	}
}
