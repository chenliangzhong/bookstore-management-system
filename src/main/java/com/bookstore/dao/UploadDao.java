package com.bookstore.dao;

import com.bookstore.bean.Upload;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadDao {

    int insert(Upload upload);
}
