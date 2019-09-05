package com.accp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.entity.SysUsers;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/authc")
public class AuthcController {
	

	@RequestMapping("/loginAjax")
	@ResponseBody
	public Object loginAjax(@RequestBody SysUsers sysUsers, HttpSession session) {
		SysUsers sysUser;
		try {
			System.out.println("sysUsers: " + JSON.toJSONString(sysUsers));
			UsernamePasswordToken token = new UsernamePasswordToken(sysUsers.getUsername(), sysUsers.getPassword());
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			sysUser = (SysUsers) SecurityUtils.getSubject().getPrincipal();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", sysUser);
			map.put("token", session.getId());
			return map;
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "0000";
		}
	}
}
