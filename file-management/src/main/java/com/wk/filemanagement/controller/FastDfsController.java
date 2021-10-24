package com.wk.filemanagement.controller;

import com.wk.filemanagement.service.FastDfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
