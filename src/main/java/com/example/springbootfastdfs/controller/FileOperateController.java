package com.example.springbootfastdfs.controller;

import com.example.springbootfastdfs.client.FastDFSClient;

import com.example.springbootfastdfs.service.FileOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;


/**
 * @Author caoerbiao
 * @Date 2020/7/10 20:10
 * @Describe
 */

@RestController
public class FileOperateController {
    @Autowired
    private FileOperateService fileOperateService;
    private FastDFSClient fastDFSClient;

    private static final Logger logger = LoggerFactory.getLogger(FileOperateController.class);


    @RequestMapping("")
    public String index(){
        return "index.html";
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws Exception{
        if (file.isEmpty()){
            return "Empty file cannot be uploaded, please select a file";
        }
        return fileOperateService.upload(file);
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response, @RequestParam  String groupName, @RequestParam  String remoteFileName) throws Exception{
        byte[] b= fastDFSClient.download(response, groupName, remoteFileName);
        if (b == null) {
            logger.error("Error1 : file not Found!");
            response.getWriter().write("Error1 : file not Found!");
        } else {
            logger.info("下载文件..");
            OutputStream out = response.getOutputStream();
            out.write(b);
            out.close();
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String groupName, @RequestParam String remoteFileName) throws Exception{
        int i = fastDFSClient.deleteFile(groupName,remoteFileName);
        if (i == 0) {
            System.out.println("FastDFS删除文件成功");
        } else {
            System.out.println("FastDFS删除文件失败");
        }
    }

}
