package com.accp.service.impl;

import com.accp.entity.SysPermissions;
import com.accp.entity.SysRoles;
import com.accp.dao.SysPermissionsDao;
import com.accp.dao.SysRolesDao;
import com.accp.service.ISysPermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljq
 * @since 2019-08-26
 */
@Service
public class SysPermissionsServiceImpl extends ServiceImpl<SysPermissionsDao, SysPermissions> implements ISysPermissionsService {
	public List<SysPermissions> queryByRolesId(List<SysRoles> list) {
		return baseMapper.queryByRolesId(list);
	}

	public List<String> queryByRolesId2(List<SysRoles> list) {
		return baseMapper.queryByRolesId2(list);
	}

	public List<SysPermissions> queryPermissionCatalogByRoleIds(List<SysRoles> list) {
		// TODO Auto-generated method stub
		return baseMapper.queryPermissionCatalogByRoleIds(list);
	}

	@Autowired
	SysPermissionsDao permissionsDao;

	@Autowired
	SysRolesDao sysRolesDao;
	
	public List<SysPermissions> queryCatalogByjsid(List<SysRoles> roleList) {
		// String username = (String) SecurityUtils.getSubject().getPrincipal();
		
		//List<SysRoles> roleList = sysRolesDao.queryByUserName("li");
		// 最顶层到节点
		SysPermissions parent = new SysPermissions();
		// System.out.println("roleList: "+JSON.toJSONString(roleList));
		List<String> pms = permissionsDao.queryByRolesId2(roleList);
		// System.out.println("pms: "+JSON.toJSONString(pms));
		boolean bool = pms.contains("*");
		// System.out.println("bool: "+bool);
		List<SysPermissions> pmsList = null;
		if (bool) {
			pmsList = permissionsDao.selectList(null);
			// System.out.println("有");
		} else {
			// System.out.println("没有");
			// 只查询目录
			pmsList = permissionsDao.queryPermissionCatalogByRoleIds(roleList);
		}
		// System.out.println("pmsList: "+pmsList);
		eachSysPermissions(pmsList, parent);
		return parent.getChildren();
	}

	// 根据用户名获取目录结构
	public List<SysPermissions> queryCatalogByUserName(String username) {
		// String username = (String) SecurityUtils.getSubject().getPrincipal();
		System.out.println("username: " + username);
		List<SysRoles> roleList = sysRolesDao.queryByUserName(username);
		// 最顶层到节点
		SysPermissions parent = new SysPermissions();
		// System.out.println("roleList: "+JSON.toJSONString(roleList));
		List<String> pms = permissionsDao.queryByRolesId2(roleList);
		// System.out.println("pms: "+JSON.toJSONString(pms));
		boolean bool = pms.contains("*");
		// System.out.println("bool: "+bool);
		List<SysPermissions> pmsList = null;
		if (bool) {
			pmsList = permissionsDao.selectList(null);
			// System.out.println("有");
		} else {
			// System.out.println("没有");
			// 只查询目录
			pmsList = permissionsDao.queryPermissionCatalogByRoleIds(roleList);
		}
		// System.out.println("pmsList: "+pmsList);
		eachSysPermissions(pmsList, parent);
		return parent.getChildren();
	}

	private void eachSysPermissions(List<SysPermissions> list, SysPermissions parent) {
		String reg = "";
		if (parent.getPermission() == null) {
			reg = "^[a-zA-Z]+$";
		} else {
			reg = "^" + parent.getPermission() + ":[a-zA-Z]+$";
		}
		for (SysPermissions s : list) {
			boolean bol = Pattern.matches(reg, s.getPermission());
			if (bol) {
				// if(s.getCatalog()==false) {
				// parent.getPerms().add(s.getPermission());
				// }else {
				parent.getChildren().add(s);
				eachSysPermissions(list, s);
				// }

			}
		}
	}

	@Override
	public List<SysPermissions> queryByRolesIdcxx(List<SysRoles> list) {
		// TODO Auto-generated method stub
		return baseMapper.queryByRolesIdcxx(list);
	}

	// public List<SysPermissions> selectByExampleAll(SysPermissionsExample example)
	// {
	// List<SysPermissions> list = permissionsMapper.selectByExample(example);
	// // System.out.println(list.size() + "大小");
	// // 最顶层到节点
	// SysPermissions parent = new SysPermissions();
	// eachSysPermissions(list, parent);
	// // 返回到是层级结构到集合
	// return parent.getChildren();
	// }
}
