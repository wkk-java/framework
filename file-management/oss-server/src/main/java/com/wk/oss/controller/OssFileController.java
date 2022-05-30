package com.wk.oss.controller;

import com.wk.entity.model.FileModel;
import com.wk.entity.result.ResultView;
import com.wk.oss.service.IAliOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/oss")
public class OssFileController {

    @Autowired
    private IAliOssService aliOssService;

    @RequestMapping(method = RequestMethod.POST)
    public ResultView upload(@RequestBody InputStream inputStream,
                             @RequestParam(value = "fileName") String fileName,
                             @RequestParam(value = "bucketName") String bucketName) {
        FileModel fileModel = aliOssService.putFile(bucketName, fileName, inputStream);
        return ResultView.success(fileModel.getLink());
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResultView upload(@RequestBody MultipartFile multipartFile, @RequestParam(value = "bucketName") String bucketName) {
        FileModel fileModel = aliOssService.putFile(bucketName, multipartFile.getOriginalFilename(), multipartFile);
        return ResultView.success(fileModel.getLink());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResultView upload(@RequestBody byte[] data,
                             @RequestParam(value = "fileName") String fileName,
                             @RequestParam(value = "bucketName") String bucketName) {
        FileModel fileModel = aliOssService.putFile(bucketName, fileName, new ByteArrayInputStream(data));
        return ResultView.success(fileModel.getLink());
    }

}
