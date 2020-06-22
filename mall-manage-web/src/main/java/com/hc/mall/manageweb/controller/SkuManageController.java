package com.hc.mall.manageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hc.mall.bean.SkuInfo;
import com.hc.mall.bean.SkuLsInfo;
import com.hc.mall.bean.SpuImage;
import com.hc.mall.bean.SpuSaleAttr;
import com.hc.mall.service.ListService;
import com.hc.mall.service.ManageService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SkuManageController {


    @Reference
    ListService listService;

    @Reference
    ManageService manageService;

    @RequestMapping("spuImageList")
    public List<SpuImage> spuList(SpuImage spuImage) {
        return manageService.getSpuImageList(spuImage);
    }

    @RequestMapping("spuSaleAttrList")
    public List<SpuSaleAttr> spuSaleAttrList(String spuId) {
        return manageService.getSpuSaleAttrList(spuId);
    }

    @RequestMapping("saveSkuInfo")
    public void saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        if (skuInfo != null){
            manageService.saveSkuInfo(skuInfo);
        }
    }

    @RequestMapping("onSale")
    public void  onSale(String skuId){
        SkuLsInfo skuLsInfo = new SkuLsInfo();

        // 获取sku商品信息
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);

        // 对拷数据
        BeanUtils.copyProperties(skuInfo,skuLsInfo);

        // 上传到es
        listService.saveSkuLsInfo(skuLsInfo);
    }
}