package com.maven.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * <p>
 * Title: logFilter
 * </p>
 * <p>
 * Description: 过滤器
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月19日 上午11:37:02
 */
public class LogFilter implements Filter {

	@Override
	public void destroy() {
		// System.out.println("********************logFilter过滤器销毁********************");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// System.out.println("logFilter执行之前执行的代码！！！");

		// 调用此方法web资源就会被访问，不调用此方法web资源就不会被访问(让目标资源执行,放行)
		chain.doFilter(request, response);

		// System.out.println("logFilter执行后执行的代码！！！");
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("********************logFilter过滤器初始化********************");
		// 获取过滤器的名字
		// String filterName = filterConfig.getFilterName();
		// 得到web.xml文件中配置的初始化参数
		// String initParameter1 = filterConfig.getInitParameter("name");
		// String initParameter2 = filterConfig.getInitParameter("like");
		// 返回过滤器的所有初始化参数的名字的枚举集合
		// Enumeration<String> initParameterNames =
		// filterConfig.getInitParameterNames();

		// System.out.println(filterName);
		// System.out.println(initParameter1);
		// System.out.println(initParameter2);
		// while (initParameterNames.hasMoreElements()) {
		// String paramName = initParameterNames.nextElement();
		// System.out.println(paramName);
		// }
	}

}
