package com.hzqing.hzqfs.download.service;

import com.hzqing.hzqfs.domain.PageData;

import java.io.IOException;
import java.io.InputStream;

public interface IDownloadService {
    String getfs();

    InputStream downFile(PageData pd) throws IOException;

    boolean isExist(String path);
}
