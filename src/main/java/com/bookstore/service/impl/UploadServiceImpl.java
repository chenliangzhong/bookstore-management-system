package com.bookstore.service.impl;


import com.bookstore.bean.Upload;
import com.bookstore.dao.UploadDao;
import com.bookstore.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadDao uploadDao;

    @Override
    public int insert(Upload upload) {
        return uploadDao.insert(upload);
    }
}
