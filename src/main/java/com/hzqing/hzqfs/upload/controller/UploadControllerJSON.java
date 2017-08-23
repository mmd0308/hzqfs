package com.hzqing.hzqfs.upload.controller;

import com.hzqing.hzqfs.hadoop.HadoopService;
import com.hzqing.hzqfs.upload.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping("/api/upload")
public class UploadControllerJSON {
    @Resource(name = "uploadService")
    private IUploadService uploadService;

    @RequestMapping(value = "/caseOne",method = RequestMethod.POST)
    public String caseOne(@RequestParam("file") MultipartFile file,String address,String filename){

        if (!file.isEmpty()) {

            try {
                uploadService.savefile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  "success";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }
}
