package com.hc.mall.manageweb.controller;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class FileUploadController {

    @Value("${fileServer.url}")
    private String fileUrl;

    // 获取上传文件，需要使用springmvc 技术
    @RequestMapping("fileUpload")
    public String fileUpload(MultipartFile file) throws IOException, MyException {
        String imgUrl = fileUrl;

        if (file != null){
            String configFile = this.getClass().getResource("/tracker.conf").getFile();
            // fastDFS文件服务器的java客户端初始化方法
            ClientGlobal.init(configFile);
            TrackerClient trackerClient = new TrackerClient();
            // 获取连接
            TrackerServer trackerServer = trackerClient.getConnection();
            StorageClient storageClient = new StorageClient(trackerServer,null);
            // 获取上传文件的名称
            String originalFilename = file.getOriginalFilename();
            // 获取文件的后缀名
            String extName = StringUtils.substringAfterLast(originalFilename, ".");
            String[] upload_file = storageClient.upload_file(file.getBytes(), extName, null);
            for (int i = 0; i < upload_file.length; i++) {
                String path = upload_file[i];

                imgUrl += "/" + path;
            }
        }
        return imgUrl;
    }
}