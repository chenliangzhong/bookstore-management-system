package com.bookstore.controller;

import com.bookstore.bean.DownloadFile;
import com.bookstore.bean.Upload;
import com.bookstore.service.DownloadFileService;
import com.bookstore.service.UploadService;
import com.bookstore.util.FileUploadUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController extends BaseApiController {

    @Autowired
    UploadService testService;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    FileUploadUtils fileUploadUtils;

    @PostMapping("/add")
    @Transactional
    public  Map<String, Object> add(@RequestParam(required = false)CommonsMultipartFile file) {

        String filePath = "";
        Upload upload = new Upload();

        if (file != null && !file.isEmpty()) {
            filePath = fileUploadUtils.getWordPath(file);
            if (filePath == null) return onBadResp("该文件不符合格式");

            upload.setFile(new DownloadFile(filePath, file.getOriginalFilename()));
            downloadFileService.insert(upload.getFile());
        }

        if (testService.insert(upload) > 0) {
            if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
            return onSuccessRep("添加成功");
        }
        return onBadResp("添加失败");
    }
}
