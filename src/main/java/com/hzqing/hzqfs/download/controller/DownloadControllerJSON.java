package com.hzqing.hzqfs.download.controller;

import com.hzqing.hzqfs.download.service.IDownloadService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DownloadControllerJSON {
    @Resource(name = "downloadService")
    private IDownloadService downloadService;

}
