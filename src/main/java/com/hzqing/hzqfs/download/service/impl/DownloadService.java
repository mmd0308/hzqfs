package com.hzqing.hzqfs.download.service.impl;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.download.service.IDownloadService;
import com.hzqing.hzqfs.hadoop.HadoopConf;
import com.hzqing.hzqfs.hadoop.HadoopService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.client.HdfsUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;


@Service("downloadService")
public class DownloadService implements IDownloadService {
    @Autowired
    private HadoopConf hadoopConf;


    public String getfs(){

        return "down";
    }

    /**
     * 下载文件，获取inputStream
     * @param pd path 文件的全路径 hdfs
     * @return
     * @throws IOException
     */
    @Override
    public InputStream downFile(PageData pd) throws IOException {
        FileSystem fs = this.getFileSystem();
        InputStream inputStream = fs.open(new Path(pd.get("path").toString()));
        return inputStream;
    }
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
