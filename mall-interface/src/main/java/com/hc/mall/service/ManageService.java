package com.hc.mall.service;

import com.hc.mall.bean.*;

import java.util.List;

/**
 * @author 老铁
 * @create 2020-06-11 16:02
 */
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


     // List<SpuInfo> getSpuList(String catalog3Id);
      //  根据spuInfo 对象属性获取spuInfo 集合
      List<SpuInfo> getSpuInfoList(SpuInfo spuInfo);

     /**
     * 获取所有的销售属性数据
     * @return
     */
     List<BaseSaleAttr> getBaseSaleAttrList();

     /**
     * 保存spuInfo
     * @param spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);


    /**
     * 查询spu的所有图片，用于sku
     * @param spuImage
     * @return
     */
    List<SpuImage> getSpuImageList(SpuImage spuImage);

    /**
     * 查询销售属性和销售属性值
     * @param spuId
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);


    /**
     * 保存sku
     * * @param skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);


    /**
     * 根据skuId 查询skuInfo
     * @param skuId
     * @return
     */
    SkuInfo getSkuInfo(String skuId);

    /**
     * 根据skuId 查询skuImage集合
     * @param skuId
     * @return
     */
    List<SkuImage> getSkuImageBySkuId(String skuId);


    /**
     * 根据skuId,spuId 查询销售属性集合
     * @param skuInfo
     * @return
     */
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(SkuInfo skuInfo);

    /**
     * 根据spuId 查询销售属性值集合
     * @param spuId
     * @return
     */
    List<SkuSaleAttrValue> getSkuSaleAttrValueListBySpu(String spuId);


    /**
     * 根据平台属性值id查询
     * @param attrValueIdList
     * @return
     */
    List<BaseAttrInfo> getAttrList(List<String> attrValueIdList);
}
