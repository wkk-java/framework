package com.wk.filemanagement.controller;

import com.wk.entity.result.ResultView;
import com.wk.filemanagement.service.FastDfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: vince
 * create at: 2021/10/23 下午12:24
 * @description: fastdfs访问层
 */
@RestController
@RequestMapping("/fastdfs")
public class FastDfsController {

    @Autowired
    public FastDfsService fastDfsService;

    @RequestMapping(method = RequestMethod.POST)
    public ResultView upload(@RequestBody InputStream inputStream) throws IOException {

        String upload = fastDfsService.upload(inputStream, null);
        return ResultView.success(upload);
    }


}
