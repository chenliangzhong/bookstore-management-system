package com.bookstore.service.impl;

import com.bookstore.bean.Upload;
import com.bookstore.dao.UploadDao;
import com.bookstore.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadDao uploadDao;

    @Override
    public int insert(Upload upload) {
        return uploadDao.insert(upload);
    }

    @Override
    public Upload selectByFileUri(String fileUri) {
        return uploadDao.selectByFileUri(fileUri);
    }

    @Override
    public Upload selectById(Long id) {
        return uploadDao.selectById(id);
    }

    @Override
    public int update(Upload upload) {
        return uploadDao.update(upload);
    }

    @Override
    public Upload selectByDeveloper(String developer) {
        return uploadDao.selectByDeveloper(developer);
    }
}
