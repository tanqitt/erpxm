package com.accp.service;

import java.util.List;

import com.accp.entity.SysPermissions;
import com.accp.entity.SysRoles;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ljq
 * @since 2019-08-26
 */
public interface ISysPermissionsService extends IService<SysPermissions> {

	public List<SysPermissions> queryByRolesId(List<SysRoles> list);

	public List<String> queryByRolesId2(List<SysRoles> list);

	public List<SysPermissions> queryPermissionCatalogByRoleIds(List<SysRoles> list);

	public List<SysPermissions> queryCatalogByUserName(String username);
	
	public List<SysPermissions> queryByRolesIdcxx(List<SysRoles> list);
}
