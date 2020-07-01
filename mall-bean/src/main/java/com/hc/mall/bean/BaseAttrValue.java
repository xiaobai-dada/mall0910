package com.hc.mall.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * @author 老铁
 * @create 2020-06-11 15:16
 */
@Data
@ToString
public class BaseAttrValue implements Serializable {
    @Id
    @Column
    private String id;
    @Column
    private String valueName;
    @Column
    private String attrId;

    @Transient
    private String urlParam;


}