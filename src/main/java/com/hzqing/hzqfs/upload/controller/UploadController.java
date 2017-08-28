package com.hzqing.hzqfs.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class UploadController {



    @RequestMapping("/uploadOne")
    public String caseOne(){
        return "upload/upload-single-one";
    }

    @RequestMapping("/mUploadOne")
    public String mcaseOne(){
        return "upload/upload-more-one";
    }



}
