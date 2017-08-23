package com.hzqing.hzqfs.download.service.impl;

import com.hzqing.hzqfs.download.service.IDownloadService;
import com.hzqing.hzqfs.hadoop.HadoopService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;



@Service("downloadService")
public class DownloadService implements IDownloadService {

    public String getfs(){

        return "down";
    }
}
