package com.travel.service.upload.service.Impl;

import com.travel.service.upload.fastdfs.StorageService;
import com.travel.service.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lhy
 * @date 2020/3/18 12:03
 * @desc:
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${fastdfs.base.url}")
    private String FASTDFS_BASE_URL;

    @Autowired
    private StorageService storageService;

    @Override
    public Map<String, Object> upload(MultipartFile dropFile) {
        Map<String, Object> result = new HashMap<>();

        // Dropzone 上传
        if (dropFile != null) {
            result.put("fileName", writeFile(dropFile));
        }


        return result;
    }

    /**
     * 将图片写入指定目录
     *
     * @param multipartFile
     * @return 返回文件完整路径
     */
    private String writeFile(MultipartFile multipartFile) {
        // 获取文件后缀
        String oName = multipartFile.getOriginalFilename();
        String extName = oName.substring(oName.lastIndexOf(".") + 1);

        // 文件存放路径
        String url = null;
        try {
            String uploadUrl = storageService.upload(multipartFile.getBytes(), extName);
            url = FASTDFS_BASE_URL + uploadUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回文件完整路径
        return url;
    }


}
