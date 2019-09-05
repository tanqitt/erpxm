package com.accp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.entity.SysPermissions;
import com.accp.entity.SysUsers;
import com.accp.service.ISysPermissionsService;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/index")
public class IndexController {

	@Autowired
	ISysPermissionsService iSysPermissionsService;

	@RequestMapping("/index")
	@ResponseBody
	public List<SysPermissions> toIndex() {
		List<SysPermissions> list = iSysPermissionsService.list(null);
		System.out.println("list: " + JSON.toJSON(list)+"=============");
		return list;
	}

	@RequestMapping("/byUser")
	@ResponseBody
	public List<SysPermissions> byUser(@RequestBody SysUsers sysUsers) {
		List<SysPermissions> list = iSysPermissionsService.queryCatalogByUserName(sysUsers.getUsername());
		System.out.println("list: " + JSON.toJSON(list));
		return list;
	}
}
