package com.live.zhf.common.entity.menu;

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

    private String  status;

    private int sort;

    private List<Menu> children;
}
