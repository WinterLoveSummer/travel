package com.travel.service.upload.controller;

import com.travel.service.upload.service.UploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 文件上传
     *
     * @param dropFile    Dropzone
     * @param
     * @return
     */
    @ApiOperation(value = "文件上传")
    @PostMapping(value = "/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, Object> upload(@RequestPart("dropFile") MultipartFile dropFile) {
        return uploadService.upload(dropFile);
    }





}
