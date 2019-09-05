package com.accp.service.impl;

import com.accp.entity.Employee;
import com.accp.dao.EmployeeDao;
import com.accp.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljq
 * @since 2019-08-28
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeDao, Employee> implements IEmployeeService {

}
