package com.maven.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;

/**
 * 自定义session管理器
 * 
 * <p>Title: CustomSessionManager</p>  
 * <p>Description: </p>  
 * @author liyongqiang  
 * @date 2018年4月30日  
 * @version 1.0
 */
public class CustomSessionManager extends DefaultWebSessionManager{

	@Override
	protected Session retrieveSession(SessionKey sessionkey) throws UnknownSessionException {
		
		Serializable sessionId = getSessionId(sessionkey);
		ServletRequest request = null;
		if(sessionkey instanceof WebSessionKey){
			request = ((WebSessionKey)sessionkey).getServletRequest();
		}
		
		
		if(request != null && sessionId != null){
			Session session = (Session)request.getAttribute(sessionId.toString());
			request.setAttribute(sessionId.toString(), session);
			if(session != null){
				return session;
			}
		}
		
		Session session = super.retrieveSession(sessionkey);
		if(request != null && sessionId != null){
			request.setAttribute(sessionId.toString(), session);
		}
		return session;
	}
	
}
