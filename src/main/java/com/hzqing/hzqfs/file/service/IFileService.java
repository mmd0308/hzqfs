package com.hzqing.hzqfs.file.service;

import com.hzqing.hzqfs.domain.PageData;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface IFileService {
    List<PageData> getFiles(PageData pd) throws IOException;

    List<PageData> listFiles(PageData pd) throws IOException;
}
