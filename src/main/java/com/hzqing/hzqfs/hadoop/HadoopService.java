package com.hzqing.hzqfs.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
public class HadoopService {
    @Autowired
    private  HadoopConf hadoopConf;

    private static HadoopConf shadoopConf;

    @PostConstruct
    public void init(){
        shadoopConf = this.hadoopConf;
    }

    public static FileSystem getFileSystem() {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS",shadoopConf.getFsdefault());
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.newInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }
}
