package com.hzqing.hzqfs.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
public class HadoopService {
    @Autowired
    protected HadoopConf hadoopConf;

    protected Configuration configuration;
    protected FileSystem fileSystem;
    {
        configuration = new Configuration();
        configuration.set("fs.defaultFS",hadoopConf.getFsdefault());
        try {
            fileSystem = FileSystem.newInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
