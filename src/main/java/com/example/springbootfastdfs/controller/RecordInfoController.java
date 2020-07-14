package com.example.springbootfastdfs.controller;

import com.example.springbootfastdfs.client.FastDFSClient;
import com.example.springbootfastdfs.entity.RecordInfoEntity;
import com.example.springbootfastdfs.service.FileOperateService;
import com.example.springbootfastdfs.service.OperateRecordTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.security.pkcs11.wrapper.Constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:58
 * @Describe
 */
@RestController
public class RecordInfoController {
    private static final Logger logger = LoggerFactory.getLogger(RecordInfoController.class);
    @Autowired
    private OperateRecordTableService operateService;

    @Autowired
    private FileOperateService fileOperateService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @PostMapping("/save")
    public List<String> save(RecordInfoEntity recordInfoEntity, MultipartFile[] file)
            throws IOException, SQLException, FileNotFoundException {
        List<String> pathList = new ArrayList<String>();
        for (MultipartFile file1:file){
            recordInfoEntity.setId(UUID.randomUUID().toString());
            RecordInfoEntity result = fileOperateService.upload(recordInfoEntity,file1);
            pathList.add(recordInfoEntity.getPath());
            operateService.save(result);
        }
        return pathList;
    }

//    @GetMapping("/look")
//    public void look(HttpServletResponse response,@RequestParam String uuid) throws Exception{
//        Optional<RecordInfoEntity> record = operateService.findById(uuid);
//        String path = record.get().getPath();
//        String groupName = path.substring(path.lastIndexOf("/"));
//        String remoteFileName = path.substring(path.lastIndexOf("/"));
//
//        byte[] b= fastDFSClient.download(response, groupName, remoteFileName);
//        if (b == null) {
//            logger.error("Error1 : file not Found!");
//            response.getWriter().write("Error1 : file not Found!");
//        } else {
//            logger.info("下载文件..");
//            OutputStream out = response.getOutputStream();
//            out.write(b);
//            out.close();
//        }
//    }
//
//    @DeleteMapping("/delete")
//    public void delete(@RequestParam String groupName, @RequestParam String remoteFileName) throws Exception{
//        int i = fastDFSClient.deleteFile(groupName,remoteFileName);
//        if (i == 0) {
//            System.out.println("FastDFS删除文件成功");
//        } else {
//            System.out.println("FastDFS删除文件失败");
//        }
//    }

}
