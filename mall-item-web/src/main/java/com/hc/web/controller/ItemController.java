package com.hc.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.hc.mall.bean.SkuInfo;
import com.hc.mall.bean.SkuSaleAttrValue;
import com.hc.mall.bean.SpuSaleAttr;
import com.hc.mall.service.ManageService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class ItemController {

    @Reference
    ManageService manageService;

    @RequestMapping("{skuId}.html")
    public String skuInfoPage(@PathVariable(value = "skuId")String skuId, Model model){

        // 所有的详细信息
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);

        // 回显信息并锁定默认的
        List<SpuSaleAttr> spuSaleAttrList = manageService.getSpuSaleAttrListCheckBySku(skuInfo);

        // 获取销售属性的Id
        List<SkuSaleAttrValue> skuSaleAttrValueList = manageService.getSkuSaleAttrValueListBySpu(skuInfo.getSpuId());

        String key = "";
        HashMap<String,Object> map = new HashMap<>();

        // 普通循环
        for (int i = 0; i < skuSaleAttrValueList.size(); i++) {
            SkuSaleAttrValue skuSaleAttrValue = skuSaleAttrValueList.get(i);

            // 如果有东西了 拼接 |
            if (key.length() > 0){
                key += "|";
            }
            // 给 key 拼接id 124|126
            key += skuSaleAttrValue.getSaleAttrValueId();

            // 当循环 skuid 与下次 skuid 不一致时停止拼接，循环完毕时停止拼接
            if ((i + 1) == skuSaleAttrValueList.size() || !skuSaleAttrValue.getSkuId().equals(skuSaleAttrValueList.get(i+1).getSkuId())){
                // 放入map集合
                map.put(key,skuSaleAttrValue.getSkuId());
                // 并且清空 key 方便下次拼接
                key = "";
            }
        }

        // 将 map 转换为 json 字符串
        String valuesSkuJson = JSON.toJSONString(map);
        System.out.println("valuesSkuJson = " + valuesSkuJson);

        // 将转换好的json数据存入request域
        model.addAttribute("valuesSkuJson",valuesSkuJson);
        // 默认锁定的spu
        model.addAttribute("spuSaleAttrList",spuSaleAttrList);
        // 保存sku详情到域
        model.addAttribute("skuInfo",skuInfo);

        return "item";
    }
}
