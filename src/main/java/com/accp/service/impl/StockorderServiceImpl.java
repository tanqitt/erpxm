package com.accp.service.impl;

import com.accp.entity.Stockorder;
import com.accp.dao.StockorderDao;
import com.accp.service.IStockorderService;
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
public class StockorderServiceImpl extends ServiceImpl<StockorderDao, Stockorder> implements IStockorderService {

}
