package com.hzqing.hzqfs.upload.controller;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.hadoop.HadoopService;
import com.hzqing.hzqfs.upload.service.IUploadService;
import com.hzqing.hzqfs.util.JsonJackUtil;
import com.hzqing.hzqfs.util.NotNUllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
@RequestMapping("/api/upload")
public class UploadControllerJSON {
    @Resource(name = "uploadService")
    private IUploadService uploadService;

    @RequestMapping(value = "/caseOne",method = RequestMethod.POST)
    public String caseOne(@RequestParam("file") MultipartFile file,String address,String sysname,String modify){
        PageData pd = new PageData();

        if (!file.isEmpty()) {
            try {
                if(NotNUllUtil.notNull(address)) {
                    pd.put("address", address);
                }else {
                    pd.put("res","error");
                    pd.put("address","存储路径不能为空");
                    return  JsonJackUtil.ObjectToJson(pd);
                }
                if(NotNUllUtil.notNull(sysname)) {
                    pd.put("sysname", sysname);
                }else {
                    pd.put("res","error");
                    pd.put("sysname","系统名称不能为空");
                    return  JsonJackUtil.ObjectToJson(pd);
                }
                if(NotNUllUtil.notNull(modify)) {
                    pd.put("modify", modify);
                }else {
                    pd.put("modify", "yes");
                }

                pd.put("file",file);
                pd = uploadService.savefile(pd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            pd.put("res","success");
            return JsonJackUtil.ObjectToJson(pd);
        } else {
            pd.put("res","error");
            pd.put("file","上传失败，因为文件是空的");
            return JsonJackUtil.ObjectToJson(pd);
        }
    }
}
