package com.accp.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accp.entity.SysUsers;
import com.accp.service.impl.SysRolesServiceImpl;
import com.accp.service.impl.SysUsersServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

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
@RequestMapping("/sysUsers")
public class SysUsersController {

	@Autowired
	private SysUsersServiceImpl Impl;
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Integer id) {
		System.out.println(id);
		boolean bl= Impl.removeById(id);
		System.out.println(bl);
		if(!bl) {
			return "0000";
		}
		return "2222";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Page<SysUsers> queryAll(Integer currentPage,String name,Integer myts) {
		//非空操作
		if(myts==null) {
			myts=1;
		}
		if(currentPage==null) {
			currentPage=1;
		}
		if(name==null) {
			name="";
		}
		Page<SysUsers> page = new Page<>(currentPage, myts);
		QueryWrapper<SysUsers> query = new QueryWrapper<SysUsers>();
		query.lambda().like(SysUsers::getUsername, name);
		Impl.page(page,query);
		
		return page;

	}
}

