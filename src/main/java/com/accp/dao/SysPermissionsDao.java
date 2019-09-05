package com.accp.dao;

import java.util.List;

import com.accp.entity.SysPermissions;
import com.accp.entity.SysRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljq
 * @since 2019-08-26
 */
public interface SysPermissionsDao extends BaseMapper<SysPermissions> {

	public List<SysPermissions> queryByRolesId(List<SysRoles> list);

	public List<String> queryByRolesId2(List<SysRoles> list);
	
	public List<SysPermissions> queryPermissionCatalogByRoleIds(List<SysRoles> list);
	
	//查询角色权限
	public List<SysPermissions> queryByRolesIdcxx(List<SysRoles> list);
}
