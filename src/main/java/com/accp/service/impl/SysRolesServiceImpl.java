package com.accp.service.impl;

import com.accp.entity.SysRoles;
import com.accp.dao.SysRolesDao;
import com.accp.service.ISysRolesService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ljq
 * @since 2019-08-26
 */
@Service
public class SysRolesServiceImpl extends ServiceImpl<SysRolesDao, SysRoles> implements ISysRolesService {
	public List<SysRoles> queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	public Page<SysRoles> queryBage(Page<SysRoles> page,String username) {
		return baseMapper.queryBage(page,username);
	}

	@Override
	public List<SysRoles> queryxq(Integer id) {
		// TODO Auto-generated method stub
		return baseMapper.queryxq(id);
	}

	@Override
	public List<SysRoles> queryByUserid(Integer id) {
		// TODO Auto-generated method stub
		return baseMapper.queryByUserid(id);
	}
}
