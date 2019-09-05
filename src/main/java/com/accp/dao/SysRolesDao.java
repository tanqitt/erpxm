package com.accp.dao;

import java.util.List;

import com.accp.entity.SysRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2019-08-26
 */
public interface SysRolesDao extends BaseMapper<SysRoles> {
	public List<SysRoles> queryByUserName(String username);
	
	public Page<SysRoles> queryBage(@Param("page") Page<SysRoles> page
			,@Param("username") String username);
	
	//查看角色详情
	public List<SysRoles> queryxq(Integer id);
	
	public List<SysRoles> queryByUserid(Integer id);
}
