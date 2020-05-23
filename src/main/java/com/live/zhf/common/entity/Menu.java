package com.live.zhf.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class Menu implements Serializable {

    private Integer id;

    private Integer parentId;

    private String path;

    private String name;

    private Meta meta;

    private String  directory;

    private String  permission;

    private String  status;

    private int sort;

//    private Boolean hasChildren;

    private List<Menu> children;
}
