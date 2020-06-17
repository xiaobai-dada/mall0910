package com.hc.mall.manageservice.mapper;


import com.hc.mall.bean.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr> {


    /**
     * 根据 spuId 查询销售属性集合
     *
     * 创建SpuSaleAttrMapper.xml ,写在resource 目录下
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> selectSpuSaleAttrList(String spuId);


    List<SpuSaleAttr> selectSpuSaleAttrListCheckBySku(String id, String spuId);

}

