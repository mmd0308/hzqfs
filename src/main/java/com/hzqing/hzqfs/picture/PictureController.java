package com.hzqing.hzqfs.picture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/picture")
public class PictureController {
    @RequestMapping("list")
    public String list(){
        return "picture/picture-list";
    }

}
