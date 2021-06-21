package com.d.erp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.d.erp.dao.GoodsDao;
import com.d.erp.entity.Goods;
import com.d.erp.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * (Goods)表服务实现类
 *
 * @author dudu.zhong
 * @since 2021-06-19 21:09:00
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

}
