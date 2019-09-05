package com.accp.service;

import java.util.List;

import com.accp.entity.SysRoles;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ljq
 * @since 2019-08-26
 */
public interface ISysRolesService extends IService<SysRoles> {
	public List<SysRoles> queryByUserName(String username);
	
	public Page<SysRoles> queryBage(Page<SysRoles> page,String name);
	
	public List<SysRoles> queryxq(Integer id);
	
	public List<SysRoles> queryByUserid(Integer id);
}
