package com.hc.mall.manageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hc.mall.bean.SpuInfo;
import com.hc.mall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SpuManageController {

    @Reference
    ManageService manageService;

    @RequestMapping("spuList")
    public List<SpuInfo> spuList(String catalog3Id) {
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        List<SpuInfo> spuInfoList = manageService.getSpuInfoList(spuInfo);
        return spuInfoList;
    }

    @RequestMapping("saveSpuInfo")
    public void saveSpuInfo(@RequestBody SpuInfo spuInfo) {
        if (spuInfo != null){
            // 调用保存
            manageService.saveSpuInfo(spuInfo);
        }
    }
}