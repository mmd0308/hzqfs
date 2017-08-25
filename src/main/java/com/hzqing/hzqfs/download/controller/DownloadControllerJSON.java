package com.hzqing.hzqfs.download.controller;

import com.hzqing.hzqfs.domain.PageData;
import com.hzqing.hzqfs.download.service.IDownloadService;
import com.hzqing.hzqfs.util.JsonJackUtil;
import com.hzqing.hzqfs.util.NotNUllUtil;
import org.mortbay.util.ajax.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/download")
public class DownloadControllerJSON {
    @Resource(name = "downloadService")
    private IDownloadService downloadService;

    /**
     * 判断下载路径是否存在
     * @param path
     * @return
     */
    @RequestMapping("/check-path")
    public String checkPath(String path){
        PageData p = new PageData();
        if(!NotNUllUtil.notNull(path)){
            p.put("res","error");
            p.put("error","文件路径不能为空！");
            return JsonJackUtil.ObjectToJson(p);
        }

        if(!downloadService.isExist(path)){
            p.put("res","error");
            p.put("error","文件不存在");

        }else{
            p.put("success","文件存在");
            p.put("res","success");
        }
        return JsonJackUtil.ObjectToJson(p);
    }

    /**
     * 案例一  下载
     * @param response
     * @param path
     * @param filename
     * @return
     */
    @RequestMapping("/case-one")
    public String caseOne(HttpServletResponse response,@RequestParam("path")String path,String filename){
        PageData p = new PageData();
        if(!downloadService.isExist(path)){
            p.put("res","文件不存在");
            return JsonJackUtil.ObjectToJson(p);
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream"); //不清楚文件下载类型
        InputStream inputStream;
        OutputStream outputStream;
        PageData pd = new PageData();
        pd.put("path",path);
        try {
            if (NotNUllUtil.notNull(filename)){
                response.setHeader("content-disposition", "attachment;filename="
                        + URLEncoder.encode(filename, "UTF-8"));
            }else{
                String filen = path.substring(path.lastIndexOf("/")+1,path.length());
                response.setHeader("content-disposition", "attachment;filename="
                        + URLEncoder.encode(filen, "UTF-8"));
            }
            inputStream = downloadService.downFile(pd);
            outputStream = response.getOutputStream();
            byte[] b = new byte[4096];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                outputStream.write(b, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        p.put("res","success");
        return JsonJackUtil.ObjectToJson(p);

    }

}
