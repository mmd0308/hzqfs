package com.hzqing.hzqfs.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }
    @RequestMapping(value = {"/error","404"})
    public String error(){
        return "404";
    }
    @RequestMapping(value = "welcome")
    public String welcom(){
        return "welcome";
    }


}
