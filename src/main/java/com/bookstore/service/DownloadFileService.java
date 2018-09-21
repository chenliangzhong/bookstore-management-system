package com.bookstore.service;

import com.bookstore.bean.DownloadFile;

public interface DownloadFileService {

    int insert(DownloadFile... file);

    DownloadFile selectById(Long id);

    DownloadFile selectByFileUri(String fileUri);

    int deleteByFileUri(String... fileUri);
}
