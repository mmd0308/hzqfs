package com.hzqing.hzqfs.upload.service.impl;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.hadoop.HadoopConf;
import com.hzqing.hzqfs.upload.service.IUploadService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

public class UploadServiceImpl implements IUploadService {
    @Autowired
    private HadoopConf hadoopConf;

    /**
     * 将上传文件写入到HDFS中
     * @param in
     * @return
     */
    @Override
    public PageData savefile(InputStream in) {
        FileSystem fs  =  this.getFileSystem();
        FSDataInputStream inputStream = new FSDataInputStream(in);

        return null;
    }

    /**
     * 获取Hadoop的FileSystem
     * @return
     */
    public FileSystem getFileSystem(){
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS",hadoopConf.getFsdefault());
        FileSystem fs = null;
        try {
            fs = FileSystem.newInstance(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fs;
    }
}
