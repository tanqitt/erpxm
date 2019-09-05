package com.accp.realm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accp.dao.SysPermissionsDao;
import com.accp.dao.SysRolesDao;
import com.accp.dao.SysUsersDao;
import com.accp.entity.SysPermissions;
import com.accp.entity.SysRoles;
import com.accp.entity.SysUsers;
import com.accp.realm.crypt.BCryptPasswordEncoder;
import com.accp.service.ISysUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	SysUsersDao sysUsersDao;

	@Autowired
	SysRolesDao sysRolesDao;

	@Autowired
	SysPermissionsDao sysPermissionsDao;

	@Autowired
	private ISysUsersService iSysUsersService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		/* String userName = (String) principals.getPrimaryPrincipal(); */
		SysUsers user = (SysUsers) getAuthenticationCacheKey(principals);
		List<SysRoles> listRole = sysRolesDao.queryByUserName(user.getUsername());
		Set<String> setRole = new HashSet<String>();
		for (SysRoles role : listRole) {
			setRole.add(role.getRole());
			System.out.println("role: " + role.getRole());
		}
		List<SysPermissions> listPermissions = sysPermissionsDao.queryByRolesId(listRole);
		Set<String> setPermissions = new HashSet<String>();
		for (SysPermissions permissions : listPermissions) {
			setPermissions.add(permissions.getPermission());
			System.out.println("permissions: " + permissions.getPermission());
		}
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(setRole);
		simpleAuthorizationInfo.setStringPermissions(setPermissions);
		System.out.println("listPermissions: " + listPermissions);
		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String userName = userToken.getUsername();
		String passWord = new String(userToken.getPassword());

		QueryWrapper<SysUsers> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("userName", userName);
		List<SysUsers> sysUsersList = iSysUsersService.list(queryWrapper);

		System.out.println("sysUsersList: " + sysUsersList.size());
		if (sysUsersList != null || sysUsersList.size() > 0) {

			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			boolean bol = bCryptPasswordEncoder.matches(passWord, sysUsersList.get(0).getPassword());
			System.out.println("bol: " + bol);
			if (bol) {
				SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUsersList.get(0),
						passWord, this.getName());
				return authenticationInfo;
			}
		}
		return null;
	}

}
