package com.accp.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component("formAuthenticationFilter")
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override

	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (request instanceof HttpServletRequest) {

			HttpServletRequest httpRequest = (HttpServletRequest) request;

			boolean bol = httpRequest.getMethod().toUpperCase().equals("OPTIONS");

			if (bol) {
				return true;
			}
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}
}
