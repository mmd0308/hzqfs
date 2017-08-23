package com.hzqing.hzqfs.upload.controller;

import com.hzqing.hzqfs.hadoop.HadoopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping("/api/upload")
public class UploadControllerJSON {

    @RequestMapping(value = "/caseOne",method = RequestMethod.POST)
    public String caseOne(@RequestParam("file") MultipartFile file,String address,String filename){

        if (!file.isEmpty()) {
            try {
                // 这里只是简单例子，文件直接输出到项目路径下。
                // 实际项目中，文件需要输出到指定位置，需要在增加代码处理。
                // 还有关于文件格式限制、文件大小限制，详见：中配置。
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(file.getOriginalFilename())));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
}
