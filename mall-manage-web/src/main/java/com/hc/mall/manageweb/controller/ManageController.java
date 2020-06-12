package com.hc.mall.manageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hc.mall.bean.BaseCatalog1;
import com.hc.mall.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("getCatalog1")
    public List<BaseCatalog1> BaseCatalog1(){
        return manageService.getCatalog1();
    }
}
