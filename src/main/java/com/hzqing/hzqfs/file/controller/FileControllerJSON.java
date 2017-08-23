package com.hzqing.hzqfs.file.controller;

import com.google.gson.JsonArray;
import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.file.service.impl.FileServiceImpl;
import com.hzqing.hzqfs.util.JsonJackUtil;
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
    private FileServiceImpl fileService;

    @RequestMapping("/list")
    public String list(String path){
        PageData pd  = new PageData();
        pd.put("path",path);
        List<PageData> files = null;
        try {
            files = fileService.getFiles(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String res = JsonJackUtil.ObjectToJson(files);
        System.out.println(res);
        return  res;
    }
}
