package com.maven.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title: InvilidCharacterFilter
 * </p>
 * <p>
 * Description: 过滤request请求中的非法字符，防止脚本攻击
 * </p>
 * 
 * @author liyongqiang
 * @date 2018年9月19日 下午4:38:08
 */
public class InvilidCharacterFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(InvilidCharacterFilter.class);
	// 需要过滤的非法字符
	private static String[] invalidCharacter = new String[] { "script", "select", "insert", "document", "window",
			"function", "delete", "update", "prompt", "alert", "create", "alter", "drop", "iframe", "link", "where",
			"replace", "function", "onabort", "onactivate", "onafterprint", "onafterupdate", "onbeforeactivate",
			"onbeforecopy", "onbeforecut", "onbeforedeactivateonfocus", "onkeydown", "onkeypress", "onkeyup", "onload",
			"expression", "applet", "layer", "ilayeditfocus", "onbeforepaste", "onbeforeprint", "onbeforeunload",
			"onbeforeupdate", "onblur", "onbounce", "oncellchange", "oncontextmenu", "oncontrolselect", "oncopy",
			"oncut", "ondataavailable", "ondatasetchanged", "ondatasetcomplete", "ondeactivate", "ondrag", "ondrop",
			"onerror", "onfilterchange", "onfinish", "onhelp", "onlayoutcomplete", "onlosecapture", "onmouse", "ote",
			"onpropertychange", "onreadystatechange", "onreset", "onresize", "onresizeend", "onresizestart", "onrow",
			"onscroll", "onselect", "onstaronsubmit", "onunload", "IMgsrc", "infarction" };

	@Override
	public void destroy() {
		// System.out.println("********************InvilidCharacterFilter销毁********************");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String parameterName = null;
		String parameterValue = null;
		// 获取请求的参数
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			parameterName = (String) parameterNames.nextElement();
			parameterValue = request.getParameter(parameterName);
			if (null != parameterName) {
				for (String str : invalidCharacter) {
					if (StringUtils.containsIgnoreCase(parameterValue, str)) {
						request.setAttribute("errorMessage", "非法字符：" + str);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.html");
						requestDispatcher.forward(request, response);
						return;
					}
				}
			}
		}
		chain.doFilter(request, response);// 执行目标资源 放行
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("********************字符串过滤器初始化********************");

	}

}
