package com.hzqing.hzqfs.file.controller;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.file.service.IFileService;
import com.hzqing.hzqfs.file.service.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    @Resource(name = "fileService")
    private FileServiceImpl fileService;


    @RequestMapping("/list")
    public String list(){
        return "file/file-list";
    }

    @RequestMapping("files")
    @ResponseBody
    public String files(){
        PageData pd = new PageData();
        pd.put("path","/");
        try {
            List<PageData> files = fileService.getFiles(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "files";
    }
}
