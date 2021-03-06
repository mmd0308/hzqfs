package com.hzqing.hzqfs.download.controller;

import com.hzqing.hzqfs.download.service.IDownloadService;
import com.hzqing.hzqfs.download.service.impl.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/download")
public class DownloadController {
    @Resource(name = "downloadService")
    private IDownloadService downloadService;

    @RequestMapping("case-one")
    public String caseOne(){


        return "download/download-case-one";
    }

    @RequestMapping("more-case-one")
    public String mcaseOne(){


        return "download/download-more-case-one";
    }

    @RequestMapping("case-one-down")
    public String caseOneDown(){


        return "download/download-case-one-down";
    }


}
