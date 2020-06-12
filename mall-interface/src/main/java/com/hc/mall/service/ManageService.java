package com.hc.mall.service;


import com.hc.mall.bean.BaseAttrInfo;
import com.hc.mall.bean.BaseCatalog1;
import com.hc.mall.bean.BaseCatalog2;
import com.hc.mall.bean.BaseCatalog3;

import java.util.List;

public interface ManageService {


    /**
     * 获取所有一级分类数据
     * @return
     */
    public List<BaseCatalog1> getCatalog1();
    /**
     * 获取所有二级分类数据，根据一级分类Id
     *  select * from baseCatalog2 where catalog1Id = ?
     * @return
     */
    public List<BaseCatalog2> getCatalog2(String catalog1Id);
    /**
     * 获取所有三级分类数据,根据二级分类Id
     * @return
     */
    public List<BaseCatalog3> getCatalog3(String catalog2Id);
    /**
     * 获取平台属性数据
     * @return
     */
    public List<BaseAttrInfo> getAttrList(String catalog3Id);


    /**
     * 获取平台属性值数据,根据平台属性Id
     * @return
     */
    //public List<BaseAttrValue> getAttrValueList(String attrId);



    //获取平台属性对象,根据平台属性Id
    public BaseAttrInfo getAttrInfo(String attrId);

    // 保存、修改平台属性值
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
}
