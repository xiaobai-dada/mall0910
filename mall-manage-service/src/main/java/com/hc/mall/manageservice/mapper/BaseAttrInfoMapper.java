package com.hc.mall.manageservice.mapper;

import com.hc.mall.bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {



    List<BaseAttrInfo> getBaseAttrInfoListByCatalog3Id(String catalog3Id);
}
