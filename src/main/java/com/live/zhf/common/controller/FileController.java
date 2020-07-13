package com.live.zhf.common.controller;

import com.live.zhf.exception.exception.NotFindFile;
import com.live.zhf.utils.Result;
import com.live.zhf.utils.ResultBuilder;
import com.live.zhf.utils.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Api(value = "文件模块",tags = "文件模块")
@Controller
public class FileController {
    @Value("${it.dog.uploadFilePath}")
    private String uploadPath;

    /**
     * 实现文件上传
     * */
    @ApiOperation(value ="文件上传" )
    @RequestMapping(value = "/api/fileUpload/{directory}",method= RequestMethod.POST)
    @ResponseBody
    public Result<String>  fileUpload(@PathVariable("directory")String directory,@RequestParam("fileName") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return null;
        }
        String fileName = file.getOriginalFilename();
        String path = uploadPath + directory + '/'  +  fileName;
        File dest = new File(path);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        file.transferTo(dest); //保存文件
        return ResultBuilder.success(path,ResultCode.SUCCESS);

    }


    /**
     * 实现多文件上传
     * */
    @ApiOperation(value ="多文件上传" )
    @RequestMapping(value="/filesUpload/{directory}",method= RequestMethod.POST)
    public Result<List<String>> filesUpload(@PathVariable("directory")String directory, HttpServletRequest request) throws IOException {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        List<String> paths = new ArrayList<String>();
        if (files.isEmpty()) {
            return null;
        }


        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String path = uploadPath + directory  + '/'  + fileName;
            if (file.isEmpty()) {
                return null;
            } else {
                File dest = new File(path);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                    file.transferTo(dest);
                    paths.add(path);

            }
        }
        return ResultBuilder.success(paths, ResultCode.SUCCESS);
    }
    @ApiOperation(value ="文件下载" )
    @GetMapping(value = "/download/{directory}")
    public void logDownload(@PathVariable String directory, String fileName, HttpServletResponse response) throws Exception {
        File file = new File(uploadPath + directory + "/" + fileName);

        if (!file.exists()) {
            throw new NotFindFile(uploadPath+fileName);
        }
        response.setContentType("application/force-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

        byte[] buffer = new byte[1024];
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            OutputStream os = response.getOutputStream();

            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }
    }
}
