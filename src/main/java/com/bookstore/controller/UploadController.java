package com.bookstore.controller;

import com.bookstore.bean.DownloadFile;
import com.bookstore.bean.Upload;
import com.bookstore.service.DownloadFileService;
import com.bookstore.service.UploadService;
import com.bookstore.util.FileUploadUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.ResultSet;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class UploadController extends BaseApiController {

    @Autowired
    UploadService uploadService;

    @Autowired
    FileUploadUtils fileUploadUtils;

    @PostMapping("/upload")
    public  Map<String, Object> add(@RequestParam(required = false)CommonsMultipartFile file, @RequestParam String version,@RequestParam String developer) {

        if (version == null || version.trim().length() == 0) return onBadResp ("版本号不能为空");
        if (developer == null || developer.trim().length() == 0) return onBadResp ("研发者不能为空");

        if (developer.equalsIgnoreCase("LM") || developer.equalsIgnoreCase("XC")){
            String filePath = "";
            Upload upload = new Upload();
            upload.setVersion(version);
            upload.setDeveloper(developer.toLowerCase());

            if (file != null && !file.isEmpty()) {
                filePath = fileUploadUtils.getAppPath(file);
                if (filePath == null) return onBadResp("该文件不符合格式");
                upload.setFileUrl(filePath);
                upload.setFileName(file.getOriginalFilename());
            }

            if (uploadService.selectByDeveloper(developer.toLowerCase()) == null) {
                if (uploadService.insert(upload) > 0) {
                    if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
                    return onSuccessRep("更新成功");
                }
            }

            Long id = uploadService.selectByDeveloper(developer).getId();
            File file1 = new File(fileUploadUtils.getBasePath() + uploadService.selectById(id).getFileUrl());

            if (uploadService.update(upload) > 0 && file1.delete()) {
                if (uploadService.insert(upload) > 0) {
                    if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
                    return onSuccessRep("更新成功");
                }
            }
                return onBadResp("更新失败");
        }else {
            return onBadResp ("研发者只能填XL或XC(不分大小写)");
        }
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, HttpServletResponse response) {
        String fileUri = request.getRequestURI();
        Upload file = uploadService.selectByFileUri(fileUri);
        if (file == null) return null;

        response.reset();
        try {
            return downloadFile(fileUploadUtils.getBasePath() + file.getFileUrl(),file.getFileName(),response);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ResponseEntity<byte[]> downloadFile(String fileUri, String filename, HttpServletResponse response) {
        File file = new File(fileUri);
        HttpHeaders headers = new HttpHeaders();

        try {
            //下载显示的文件名，解决文件乱码问题
//            String downlaodFilename = URLEncoder.encode(filename); //new String(filename.getBytes("UTF-8"), "iso-8859-1");
            String downlaodFilename = new String(filename.getBytes("UTF-8"), "iso-8859-1");

            //通知浏览器以attachment（下载方式）
            headers.setContentDispositionFormData("attachment", downlaodFilename);
            //二进制流数据
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + downlaodFilename);
            return new ResponseEntity<byte []>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
