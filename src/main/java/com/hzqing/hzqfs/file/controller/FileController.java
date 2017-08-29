package com.hzqing.hzqfs.file.controller;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.file.service.IFileService;
import com.hzqing.hzqfs.file.service.impl.FileServiceImpl;
import com.hzqing.hzqfs.util.NotNUllUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    @Resource(name = "fileService")
    private IFileService fileService;


    @RequestMapping("/list-one")
    public ModelAndView listOne(String path){
        ModelAndView mv = new ModelAndView();
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
        mv.addObject("lists",files);
        mv.addObject("path",path);
        mv.setViewName("file/file-list-one");
        return mv;
    }
    @RequestMapping("/list-two")
    public ModelAndView listTwo(@RequestParam("path") String path){
        ModelAndView mv = new ModelAndView();

        if(path=="'/'" || path.equals("'/'")){
            path = path.substring(1,2);
        }
        PageData pd = new PageData();
        List<PageData> files = null;
        pd.put("path",path);
        try {
            files = fileService.getFiles(pd);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mv.addObject("lists",files);
        mv.addObject("path",path);
        mv.setViewName("file/file-list-two");
        return mv;
    }



}
