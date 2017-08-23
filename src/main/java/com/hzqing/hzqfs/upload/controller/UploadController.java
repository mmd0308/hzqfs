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

}
