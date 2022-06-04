package com.it.api.controller;

import com.it.api.common.Result;
import com.it.api.utils.QiniuUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @Author: china wu
 * @Description:
 * @Date: 2022/5/16 5:02
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private QiniuUtils qiniuUtils;

    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) {
        // 获取原文件名
        String originalFilename = file.getOriginalFilename();
        // 生成新文件名
        String filename = UUID.randomUUID().toString() + "." +
                StringUtils.substringAfterLast(originalFilename, ".");
        boolean upload = qiniuUtils.upload(file, filename);
        if (upload) {
            return Result.success(QiniuUtils.URL + filename);
        }
        return Result.fail(20001, "上传失败");
    }

}
