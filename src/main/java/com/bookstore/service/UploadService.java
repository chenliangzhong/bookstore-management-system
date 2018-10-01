package com.bookstore.service;

import com.bookstore.bean.Upload;

import java.util.List;

public interface UploadService {

    int insert(Upload upload);

    Upload selectByFileUri(String fileUri);

    Upload selectById(Long id);

    int update(Upload upload);

    Upload selectByDeveloper(String developer);

    List<Upload> select();

    List<Upload> listDeveloper(String developer);
}
