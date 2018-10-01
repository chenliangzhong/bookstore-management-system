package com.bookstore.controller;

import com.bookstore.util.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    FileUploadUtils fileUploadUtils;

    @GetMapping("/upload/**")
    public void image(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String fileUrl = request.getRequestURI();
        File file = new File(fileUploadUtils.getBasePath() + fileUrl);
        if (file.exists()){
            FileInputStream in = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        }
    }
}
