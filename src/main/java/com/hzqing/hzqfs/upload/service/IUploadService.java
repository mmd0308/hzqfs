package com.hzqing.hzqfs.upload.service;

import com.hzqing.hzqfs.domain.PageData;

import java.io.InputStream;

public interface IUploadService {

    public PageData savefile(InputStream in);
}
