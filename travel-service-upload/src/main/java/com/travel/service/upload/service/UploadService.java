package com.travel.service.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author lhy
 * @date 2020/3/18 12:01
 * @desc:
 */
public interface UploadService {

    Map<String, Object> upload(MultipartFile dropFile);
}
