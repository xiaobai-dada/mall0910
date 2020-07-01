package com.hc.mall.manageservice.mapper;

import com.hc.mall.bean.SkuSaleAttrValue;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SkuSaleAttrValueMapper extends Mapper<SkuSaleAttrValue>{

    List<SkuSaleAttrValue> selectSkuSaleAttrValueListBySpu(String spuId);
}
