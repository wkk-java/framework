package com.wk.filemanagement.service;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class FastDfsService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    public String upload(MultipartFile multipartFile) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
        return storePath.getFullPath();
    }

    public String upload(byte[] bytes, String fileName) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), FilenameUtils.getExtension(fileName), null);
        return storePath.getFullPath();
    }

    public String upload(InputStream inputStream, String fileName) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, inputStream.available(), FilenameUtils.getExtension(fileName), null);
        return storePath.getFullPath();
    }

}