package com.hzqing.hzqfs.util;

import java.util.UUID;

public class UUIDUtil {

    /**
     * 获取32位uuid
     * @return
     */
    public static String get32UUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
