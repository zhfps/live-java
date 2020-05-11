package com.live.zhf.learn;

import java.lang.reflect.Field;

public class TestUtil {
    public static void valid(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields){
            Test test = field.getAnnotation(Test.class);//获取属性上的@Test注解
            if(test != null){
                field.setAccessible(true);//设置属性可访问

                if("class java.lang.String".equals(field.getGenericType().toString())){//字符串类型的才判断长度
                    String value = (String)field.get(obj);
                    if(value != null && ((value.length() > test.max()) || value.length() < test.min())){
                        System.out.println(test.description());
                    }
                }
            }
        }

    }
}
