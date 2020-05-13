package com.live.zhf.learn;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class IoTest {
    public static void readerFile(String path){
        File file = new File(path);
        String encoding = "UTF-8";

        // 01.把文件的所有内容放到String里,第二个参数是编码可选的..
        System.out.println("-------------把文件的所有内容读到String中---------");
        try {
            String content = FileUtils.readFileToString(file, encoding);
            System.out.println(content);
            FileUtils.write(file,"xxx",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
