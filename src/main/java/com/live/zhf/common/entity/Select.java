package com.live.zhf.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class Select<T,V> {
    public T value;
    public V label;
    public List<Select<T,V>> children;
}
