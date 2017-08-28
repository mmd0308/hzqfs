package com.hzqing.hzqfs.file.service.impl;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.file.service.IFileService;
import com.hzqing.hzqfs.hadoop.HadoopConf;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements IFileService {

    @Autowired
    private HadoopConf hadoopConf;


    /**
     * 逐级别获取文件夹或者文件  不递归
     * @param pd
     * @return
     * @throws IOException
     */
    @Override
    public List<PageData> getFiles(PageData pd) throws IOException {
        FileSystem fs = this.getFileSystem();
        FileStatus[] fileStatuses = fs.listStatus(new Path(pd.get("path").toString()));
        List<PageData> pds = new ArrayList<>();
        for (int i = 0; i < fileStatuses.length; i++) {
            FileStatus fileStatus = fileStatuses[i];
            PageData rpd = new PageData();
            if(fileStatus.isDirectory()){
                rpd.put("permission","d"+fileStatus.getPermission().toString());
            }else {
                rpd.put("permission","-"+fileStatus.getPermission().toString());
            }
            String path = fileStatus.getPath().toString();
            rpd.put("path",path.substring(path.lastIndexOf("/")+1,path.length()));
            rpd.put("fileSize",(fileStatus.getLen()/1024/1024)+"MB");
            pds.add(rpd);
        }

        return pds;
    }

    /**
     * 获取所有的文件  递归获取
     * @param pd
     * @return
     * @throws IOException
     */
    @Override
    public List<PageData> listFiles(PageData pd) throws IOException {
        FileSystem fs = this.getFileSystem();
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path(pd.get("path").toString()), true);
        List<PageData> lpd = new ArrayList<PageData>();
        while(listFiles.hasNext()){
            PageData p = new PageData();
            LocatedFileStatus next = listFiles.next();
            p.put("path",next.getPath().toString());
            p.put("permission",next.getPermission().toString());
            p.put("fileSize",(next.getLen()/1024/1024)+"MB");
            lpd.add(p);
        }
        return lpd;
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
