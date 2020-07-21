package com.live.zhf;

import com.google.gson.Gson;
import com.live.zhf.learn.AnnoationTest;
import com.live.zhf.learn.IoTest;
import com.live.zhf.learn.TestUtil;
import com.live.zhf.leetcode.Solution;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

@SpringBootTest
class LiveJavaApplicationTests {


    @Test
    void netTest() throws IOException {
        URL url = new URL("https://www.hao123.com/");

        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        OutputStream out = new FileOutputStream("D:\\project\\logs\\xx.html");
        byte[] arr = new byte[1024];
        int count =0;
        while ((count=in.read(arr))!=-1){
            out.write(arr,0,count);
        }
        in.close();
        out.close();
    }
}
