package com.hzqing.hzqfs.util;


public class NotNUllUtil {
    /**
     * 非空判断
     * @param str
     * @return
     */
    public static boolean notNull(String str){
        boolean res = false;
        if (null != str && str != "null" && !str.equals("null") && str != "" && !str.equals("")){
            res = true;
        }
        return res;
    }
}
