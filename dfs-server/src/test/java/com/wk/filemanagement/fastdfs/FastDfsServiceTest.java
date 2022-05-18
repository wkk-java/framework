package com.wk.filemanagement.fastdfs;

import com.wk.filemanagement.JunitApplicationRunner;
import com.wk.filemanagement.service.FastDfsService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author: vince
 * create at: 2021/10/23 下午5:53
 * @description:
 */
@Log4j2
public class FastDfsServiceTest extends JunitApplicationRunner {

    @Autowired
    private FastDfsService fastDfsService;

    @Test
    public void updateExcelFile() throws Exception {
        String filePath = "/Users/vince/Documents/规划.xlsx";
        File file = new File(filePath);
        InputStream fis = new FileInputStream(file);
        String fastdfsUrl = fastDfsService.upload(fis, "规划.xlsx");
        log.info("上传地址:{}", fastdfsUrl);
    }

    @Test
    public void updateImageFile() throws Exception {
        String filePath = "/Users/vince/Documents/design/福州二代征信时序图.jpg";
        File file = new File(filePath);
        InputStream fis = new FileInputStream(file);
        String fastdfsUrl = fastDfsService.upload(fis, "时序图.jpg");
        log.info("上传地址: {}", fastdfsUrl);
    }


    @Test
    public void updateBytes() throws Exception {
        String filePath = "/Users/vince/Documents/design/福州二代征信时序图.jpg";
        File file = new File(filePath);
        InputStream fis = new FileInputStream(file);
        String fastdfsUrl = fastDfsService.upload(fis, "时序图.jpg");
        log.info("上传地址: {}", fastdfsUrl);
    }

    public static void main(String[] args) throws Exception{
//        String filePath = "/Users/vince/Documents/design/福州二代征信时序图.jpg";
//        File imageFile = new File(filePath);
//        System.out.println(imageFile.length());
//        InputStream inputStream = IOUtils.toInputStream(filePath);
    }
}
