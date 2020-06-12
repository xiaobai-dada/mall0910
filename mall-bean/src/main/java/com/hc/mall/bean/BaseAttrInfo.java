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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String attrName;

    @Column
    private String catalog3Id;

    @Transient
    private List<BaseAttrValue> attrValueList;
}
