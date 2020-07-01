package com.hc.mall.manageservice.mapper;

import com.hc.mall.bean.BaseAttrInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author 老铁
 * @create 2020-06-11 15:22
 */
public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {



    /**
     * 根据三级分类Id查询平台属性集合
     * @param catalog3Id
     * @return
     */
    List<BaseAttrInfo> getBaseAttrInfoListByCatalog3Id(String catalog3Id);


    /**
     * 平台属性值Id 查询数据
     * @param valueIds
     * @return
     */
    // 此处必须要用@Param注解否则${ }无法识别
    List<BaseAttrInfo> selectAttrInfoListByIds(@Param("valueIds") String valueIds);
}
