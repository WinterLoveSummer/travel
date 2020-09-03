package com.travel.service.sso.service.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Component
@FeignClient(value = "TRAVEL-SERVICE-UPLOAD")
@RequestMapping(value = "/v1/upload")
public interface UploadService {

    /**
     * 文件上传
     * @param dropFile
     * @param editorFiles
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    Map<String, Object> upload(@RequestParam(value = "dropFile",required = false) MultipartFile dropFile, @RequestParam(value = "editorFiles",required = false) MultipartFile[] editorFiles);


}
