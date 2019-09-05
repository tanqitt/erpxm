package com.accp.service.impl;

import com.accp.entity.Payment;
import com.accp.dao.PaymentDao;
import com.accp.service.IPaymentService;
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
public class PaymentServiceImpl extends ServiceImpl<PaymentDao, Payment> implements IPaymentService {

}
