package com.hzqing.hzqfs.upload.service.impl;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.hadoop.HadoopConf;
import com.hzqing.hzqfs.upload.service.IUploadService;
import com.hzqing.hzqfs.util.UUIDUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service("uploadService")
public class UploadServiceImpl implements IUploadService {
    @Autowired
    private HadoopConf hadoopConf;

    /**
     * 将上传文件写入到HDFS中
     * @param p
     * @return
     */
    @Override
    public PageData savefile(PageData p) throws IOException {
        FileSystem fs  =  this.getFileSystem();
        MultipartFile file = (MultipartFile) p.get("file");
        String address = (String) p.get("address");
        String sysname = (String) p.get("sysname");
        String modify = (String) p.get("modify");
        String savePath = hadoopConf.getFsdefault();
        if(!modify.equals("yes")){
            savePath = hadoopConf.getFsdefault()+sysname+"/"+address+"/"+file.getOriginalFilename();

        }else{
            savePath = hadoopConf.getFsdefault()+sysname+"/"+address+"/"+ UUIDUtil.get32UUID();
        }
        FSDataOutputStream outputStream = fs.create(new Path(savePath));
        IOUtils.copyBytes(file.getInputStream(),outputStream,1024,true);

        PageData pd = new PageData();
        pd.put("filePath",savePath);
        pd.put("fileName",file.getOriginalFilename());
        pd.put("modifyTime",new Date());

        return pd;
    }

    /**
     * 多文件保存
     * @param p
     * @return
     * @throws IOException
     */
    @Override
    public List<PageData> msavefile(PageData p) throws IOException{
        FileSystem fs = this.getFileSystem();
        MultiValueMap<String, MultipartFile> fileMap = (MultiValueMap<String, MultipartFile>) p.get("file");
        String address = (String) p.get("address");
        String sysname = (String) p.get("sysname");
        String modify = (String) p.get("modify");
        List<MultipartFile> files = fileMap.get("file");
        String savePath = hadoopConf.getFsdefault()+sysname+"/"+address+"/";
        List<PageData> pds = new ArrayList<PageData>();
        for (MultipartFile file:
                files) {
            if (!file.isEmpty()){
                if(!modify.equals("yes")){
                    savePath = savePath + file.getOriginalFilename();
                }else{
                    savePath = savePath + UUIDUtil.get32UUID();
                }
                FSDataOutputStream outputStream = fs.create(new Path(savePath));
                IOUtils.copyBytes(file.getInputStream(),outputStream,1024,true);
                PageData pd = new PageData();
                pd.put("filePath",savePath);
                pd.put("fileName",file.getOriginalFilename());
                pd.put("modifyTime",new Date());
                pds.add(pd);
            }
        }
        return pds;
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
