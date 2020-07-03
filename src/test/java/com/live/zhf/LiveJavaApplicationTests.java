package com.live.zhf;

import com.google.gson.Gson;
import com.live.zhf.learn.AnnoationTest;
import com.live.zhf.learn.IoTest;
import com.live.zhf.learn.TestUtil;
import com.live.zhf.leetcode.Solution;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootTest
class LiveJavaApplicationTests {

    @Test
    void Test(){
        int[] arr1 = new int[]{1,2,3,4,5,6};
        int[] arr3 = new int[]{1,2,7};
        System.out.println(Arrays.toString(Contact(arr1,arr3)));
    }

    public int[] Contact(int[] A,int[] B){
        int i=0,j=0,k=0;
        int[] arr = new int[A.length+B.length];

        while(i<A.length&&j<B.length){
            if(A[i]<B[j])
                arr[k]= A[i++];
            else
                arr[k]= B[j++];
            k++;
        }
        while(i<A.length){
            arr[k]= A[i++];
            k++;
        }
        while(j<B.length){
            arr[k]= B[j++];
            k++;
        }
        return arr;
    }
    //测试 @interface
    @Test
    void contextLoads() {
        AnnoationTest annotation = new AnnoationTest();
        annotation.setName("abc");
        annotation.setPassdword("12345678901");
        try {
            TestUtil.valid(annotation);
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }

    }
    // 测试反射
    @Test
    void classTest() {
        // 方法一
//        Class annoatinCalss = new AnnoationTest().getClass();
//        System.out.println(annoatinCalss.getName());
        // 方法二
//        Class annoationClass = AnnoationTest.class;
//        System.out.println(annoationClass.getName());
        // 方法三
//        try {
//            Class annoationClass = Class.forName("com.live.zhf.learn.AnnoationTest");
//            System.out.println(annoationClass.getName());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        //通过反射调用构造器
//        try {
//            Class annoationClass = Class.forName("com.live.zhf.learn.AnnoationTest");
//            Field[] fields = annoationClass.getDeclaredFields();
//            Method[] methods =annoationClass.getDeclaredMethods();
//            Gson gson = new Gson();
//            String json = gson.toJson(fields);
//             System.out.println(methods[0]);
//            Constructor[] constructors = annoationClass.getConstructors();
//            System.out.println(constructors.length);
//            Constructor constructor = annoationClass.getConstructor(String.class, String.class);
//            System.out.println(constructor);
            //调用构造方法
//            AnnoationTest test =(AnnoationTest)constructor.newInstance("aadddd","xxxx");
//            Gson gson = new Gson();
//            String json = gson.toJson(test);
//            System.out.println(json);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    // 测试IO
    @Test
    void ioTest(){
        IoTest.readerFile("D:/tcl/data.txt");
    }

    @Test
    void LeetCode001(){
       int[] nums = {2, 7, 11, 15};
        System.out.println(new Solution().twoSum(nums, 13));
    }
}
