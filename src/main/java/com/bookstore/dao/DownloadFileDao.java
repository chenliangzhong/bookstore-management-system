package com.bookstore.dao;

import com.bookstore.bean.DownloadFile;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadFileDao {

    int insert(DownloadFile... files);

    DownloadFile selectById(Long id);

    DownloadFile selectByFileUri(String fileUri);

    int deleteByFileUri(String... fileUri);
}
