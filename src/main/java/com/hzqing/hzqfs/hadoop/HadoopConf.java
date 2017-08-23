package com.hzqing.hzqfs.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ConfigurationProperties(prefix = "hadoop")
public class HadoopConf {
    private String fsdefault;

    public String getFsdefault() {
        return fsdefault;
    }


    public void setFsdefault(String fsdefault) {
        this.fsdefault = fsdefault;
    }

}
