package com.live.zhf.common.entity;

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

    private List<Menu> children;
}
