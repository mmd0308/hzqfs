package com.hzqing.hzqfs.file.controller;

import com.google.gson.JsonArray;
import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.file.service.IFileService;
import com.hzqing.hzqfs.file.service.impl.FileServiceImpl;
import com.hzqing.hzqfs.util.JsonJackUtil;
import com.hzqing.hzqfs.util.NotNUllUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileControllerJSON {
    @Resource(name = "fileService")
    private IFileService fileService;

    @RequestMapping("/list-one")
    public String listOne(String path){
        PageData pd  = new PageData();
        if (!NotNUllUtil.notNull(path)){
            path = "/";
        }
        pd.put("path",path);
        List<PageData> files = null;
        try {
            files = fileService.listFiles(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String res = JsonJackUtil.ObjectToJson(files);
        System.out.println(res);
        return  res;
    }

    @RequestMapping("/list-two")
    public String listTwo(String path){
        PageData pd  = new PageData();
        if (!NotNUllUtil.notNull(path)){
            path = "/";
        }
        pd.put("path",path);
        List<PageData> files = null;
        try {
            files = fileService.getFiles(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String res = JsonJackUtil.ObjectToJson(files);
        return  res;
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    @RequestMapping("/delete")
    public String deleteFile(String path){
        PageData pd = new PageData();
        pd.put("path",path);
        try {
            boolean res = fileService.deleteFile(pd);
            if (res){
                pd.put("res","success");
            }else {
                pd.put("res","error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonJackUtil.ObjectToJson(pd);

    }
}
