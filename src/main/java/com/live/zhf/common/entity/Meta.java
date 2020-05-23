package com.live.zhf.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class Meta implements Serializable {

    private String title;

    private String icon;

    private String color;
}
