package com.hzqing.hzqfs.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

public class HadoopService {
    @Autowired
    private HadoopConf hadoopConf;


    /**
     * 文件是否存在
     * @param path
     * @return true 存在  false  不存在
     */
    public  boolean isExist(String path){
        boolean res = false;
        FileSystem fs = this.getFileSystem();
        try {
            res = fs.exists(new Path(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  res;
    }



    public FileSystem getFileSystem() {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS",hadoopConf.getFsdefault());
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.newInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSystem;
    }
}
