package com.hzqing.hzqfs.upload.service;

import com.hzqing.hzqfs.domain.PageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadService {

    public PageData savefile(MultipartFile in) throws IOException;
}
