package com.hzqing.hzqfs.download;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/download")
public class DownloadController {
    private DownloadService downloadService;
    @Autowired
    public void setDownloadService(DownloadService downloadService) {
        this.downloadService = downloadService;
    }
    @RequestMapping("/down")
    @ResponseBody
    public String down(){
        String getfs = downloadService.getfs();
        System.out.println(getfs);
        return getfs;
    }

}
