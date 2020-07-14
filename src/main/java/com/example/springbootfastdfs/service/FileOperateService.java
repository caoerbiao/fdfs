package com.example.springbootfastdfs.service;

import com.example.springbootfastdfs.client.FastDFSClient;
import com.example.springbootfastdfs.config.FastDFSFileConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * @Author caoerbiao
 * @Date 2020/7/10 20:14
 * @Describe
 */
@Service
public class FileOperateService {
    private static final Logger logger = LoggerFactory.getLogger(FileOperateService.class);
    // 上传
    public String upload( MultipartFile multipartFile) throws IOException, SQLException {
        String fileAbsolutePath[] = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        byte[] buffer = null;
        InputStream inputStream  = null;
        try{
            inputStream = multipartFile.getInputStream();
            int len = inputStream.available();
            buffer = new byte[len];
            inputStream.read(buffer);
            FastDFSFileConfig fastDFSFile = new FastDFSFileConfig(fileName,buffer,ext);
            FastDFSClient fastDFSClient = new FastDFSClient();
            fileAbsolutePath = fastDFSClient.upload(fastDFSFile);
            if (fileAbsolutePath == null){
                logger.error("upload file fail ! please try again");
            }
        }catch (Exception e){
            logger.error("upload file exception",e);
        }finally {
            inputStream.close();
        }
        // 获取组名
        String groupName = fileAbsolutePath[0];
        // 获取文件存储路径
        String remoteFileName = fileAbsolutePath[1];
        String path = groupName+"/"+ remoteFileName;
        return path;
    }

}
