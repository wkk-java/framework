package com.wk.oss.service;

import com.wk.entity.model.FileModel;
import com.wk.oss.model.OssFile;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface IAliOssService {
    @SneakyThrows
    void makeBucket(String bucketName);

    @SneakyThrows
    void removeBucket(String bucketName);

    @SneakyThrows
    boolean bucketExists(String bucketName);

    @SneakyThrows
    void copyFile(String bucketName, String fileName, String destBucketName);

    @SneakyThrows
    void copyFile(String bucketName, String fileName, String destBucketName, String destFileName);

    @SneakyThrows
    OssFile statFile(String bucketName, String fileName);

    @SneakyThrows
    String filePath(String bucketName, String fileName);

    @SneakyThrows
    String fileLink(String bucketName, String fileName);

    @SneakyThrows
    FileModel putFile(String bucketName, MultipartFile file);

    @SneakyThrows
    FileModel putFile(String bucketName, String prefix, MultipartFile file);

    @SneakyThrows
    FileModel putFile(String bucketName, String fileName, InputStream stream);

    @SneakyThrows
    FileModel putFile(String bucketName, String prefix, String fileName, MultipartFile file);

    @SneakyThrows
    FileModel putFile(String bucketName, String prefix, String fileName, InputStream stream);

    @SneakyThrows
    FileModel put(String bucketName, InputStream stream, String prefix, String key, boolean cover);

    @SneakyThrows
    void removeFile(String bucketName, String fileName);

    @SneakyThrows
    void removeFiles(String bucketName, List<String> fileNames);

    String getUploadToken(String bucketName);

    String getUploadToken(String bucketName, long expireTime);

    String getOssHost(String bucketName);
}
