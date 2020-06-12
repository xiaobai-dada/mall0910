package com.hc.mall.manageservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.hc.mall.bean.BaseAttrInfo;
import com.hc.mall.bean.BaseCatalog1;
import com.hc.mall.bean.BaseCatalog2;
import com.hc.mall.bean.BaseCatalog3;
import com.hc.mall.manageservice.mapper.*;
import com.hc.mall.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    @Autowired
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Autowired
    BaseCatalog2Mapper baseCatalog2Mapper;

    @Autowired
    BaseCatalog3Mapper baseCatalog3Mapper;


    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        return null;
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        return null;
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {
        return null;
    }

    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        return null;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

    }
}
