package com.hzqing.hzqfs.upload.service;

import com.hzqing.hzqfs.domain.PageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IUploadService {

    public PageData savefile(PageData pd) throws IOException;

    List<PageData> msavefile(PageData pd) throws IOException;
}
