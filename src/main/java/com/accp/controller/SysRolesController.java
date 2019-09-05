package com.accp.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.dao.SysPermissionsDao;
import com.accp.dao.SysRolesDao;
import com.accp.dao.SysUsersDao;
import com.accp.entity.SysPermissions;
import com.accp.entity.SysRoles;
import com.accp.service.ISysUsersService;
import com.accp.service.impl.SysPermissionsServiceImpl;
import com.accp.service.impl.SysRolesServiceImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljq
 * @since 2019-08-28
 */
@Controller
@RequestMapping("/sysRoles")
public class SysRolesController {

	@Autowired
	private SysRolesServiceImpl impl;
	
	@Autowired
	SysUsersDao sysUsersDao;

	@Autowired
	SysRolesDao sysRolesDao;

	@Autowired
	SysPermissionsDao sysPermissionsDao;

	@Autowired
	private ISysUsersService iSysUsersService;
	
	@Autowired
	SysPermissionsServiceImpl simpl;
	
	@RequestMapping("/query")
	@ResponseBody
	public Page<SysRoles> query(Integer currentPage,String name,Integer myts){
		if(myts==null) {
			myts=2;
		}
		if(currentPage==null) {
			currentPage=1;
		}
		if(name==null) {
			name="";
		}
		Page<SysRoles> page1 = new Page<>(currentPage, myts);
		Page<SysRoles> page = impl.queryBage(page1, name);
		System.out.println(JSON.toJSONString(page));
		return page;
	}
	
	@RequestMapping("/queryxq")
	@ResponseBody
	public List<SysPermissions> queryxq(Integer id){
		return simpl.queryCatalogByjsid(impl.queryByUserid(id));
	}
	
	@RequestMapping("/queryxqAll")
	@ResponseBody
	public List<SysPermissions> queryxqAll(){
		System.out.println(simpl.queryCatalogByjsid(impl.queryByUserid(1))+"+============");
		return simpl.queryCatalogByjsid(impl.queryByUserid(1));
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete() {

		return "";
	}
	
	
	public List<SysPermissions> selectByExample(SysPermissions example) {
//		List<SysPermissions> list = mapper.selectByExample(example);
//		//最顶层到节点
//		SysPermissions parent = new SysPermissions();
//		eachSysPermissions(list,parent);
//		//返回到是层级结构到集合
//		return parent.getChilds();
		return null;
	}
	
	
	
	private void eachSysPermissions(List<SysPermissions> list,SysPermissions parent) {
		String reg = "";
		if(parent.getPermission()==null) {
			reg = "^[a-zA-Z]+$";
		}else {
			reg = "^"+parent.getPermission()+":[a-zA-Z]+$";
		}
		for(SysPermissions s:list) {
			boolean bol = Pattern.matches(reg, s.getPermission());
			if(bol) {
				System.out.println(1);
				parent.getChildren().add(s);
				eachSysPermissions(list,s);
			}
		}
	}
	
}

