package com.accp.realm;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

@Component("sessionManager")
public class MySessionManager extends DefaultWebSessionManager {

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
		// sessionid
		String token = WebUtils.toHttp(request).getHeader("token");
		System.out.println("token: " + token);
		if (token != null && !token.equals("")) {
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, true);
			return token;
		}
		return super.getSessionId(request, response);
	}
}
