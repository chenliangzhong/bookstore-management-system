package com.bookstore.dao;

import com.bookstore.bean.Upload;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadDao {

    int insert(Upload upload);

    Upload selectByFileUri(String fileUri);

    Upload selectById(Long id);

    int update(Upload upload);

    Upload selectByDeveloper(String developer);
}
