package com.hc.mall.manageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hc.mall.bean.SkuInfo;
import com.hc.mall.bean.SpuImage;
import com.hc.mall.bean.SpuSaleAttr;
import com.hc.mall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SkuManageController {

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
}