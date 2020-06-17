package com.hc.mall.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Data
@ToString
public class BaseAttrInfo implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 获取主键自增！
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;

    // baseAttrValue 的集合
    @Transient //  表示当前属性 不是 表中的字段，是当前业务需要使用的。              不需要序列化
    private List<BaseAttrValue> attrValueList;


}