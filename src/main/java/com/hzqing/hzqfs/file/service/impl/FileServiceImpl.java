package com.hzqing.hzqfs.file.service.impl;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.file.service.IFileService;
import com.hzqing.hzqfs.hadoop.HadoopConf;
import com.sun.org.apache.xpath.internal.operations.String;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service("fileService")
public class FileServiceImpl implements IFileService {

    @Autowired
    private HadoopConf hadoopConf;



    @Override
    public List<PageData> getFiles(PageData pd) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS",hadoopConf.getFsdefault());
        FileSystem fs = FileSystem.newInstance(configuration);
        FileStatus[] fileStatuses = fs.listStatus(new Path(pd.get("path").toString()));
        List<PageData> pds = new ArrayList<PageData>();
        for (int i = 0; i < fileStatuses.length; i++) {
            FileStatus fileStatus = fileStatuses[i];
            PageData rpd = new PageData();
            rpd.put("permission",fileStatus.getPermission().toString());
            rpd.put("mtime",fileStatus.getModificationTime());
            System.out.println(fileStatus.getPermission().toString());
            System.out.println(fileStatus);
            pds.add(rpd);
        }

        return pds;
    }

}
