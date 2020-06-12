package com.hc.mall.bean;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@ToString
public class BaseCatalog1 implements Serializable {

    @Id
    @Column
    private String id;
    @Column
    private String name;
}
