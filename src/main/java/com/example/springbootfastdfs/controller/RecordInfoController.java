package com.example.springbootfastdfs.controller;

import com.example.springbootfastdfs.Bean.Result;
import com.example.springbootfastdfs.client.FastDFSClient;
import com.example.springbootfastdfs.entity.PictureInfoEntity;
import com.example.springbootfastdfs.entity.RecordInfoEntity;
import com.example.springbootfastdfs.service.FileOperateService;
import com.example.springbootfastdfs.service.RecordTableService;
import com.example.springbootfastdfs.service.PictureInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:58
 * @Describe
 */

@RestController
public class RecordInfoController {
    private static final Logger logger = LoggerFactory.getLogger(RecordInfoController.class);
    @Autowired
    private RecordTableService operateService;

    @Autowired
    private PictureInfoService pictureInfoService;

    @Autowired
    private FileOperateService fileOperateService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @PostMapping("/save")
    public void save(RecordInfoEntity recordInfoEntity, MultipartFile[] file)
            throws IOException, SQLException, FileNotFoundException {
        for (MultipartFile file1:file){
            //判断文件是否为空
            if (!file1.isEmpty() && file1.getSize()>0) {
                PictureInfoEntity pictureInfoEntity = new PictureInfoEntity();
                // 上传文件获取文件存储路径
                String Path = fileOperateService.upload(file1);
                pictureInfoEntity.setPath(Path);
                pictureInfoEntity.setRecordInfoEntity(recordInfoEntity);
                recordInfoEntity.getPictureInfoEntities().add(pictureInfoEntity);
            }
        }
        //保存信息
        operateService.save(recordInfoEntity);
    }

    //未分页
    @GetMapping("/getRecord")
    public List<RecordInfoEntity> look(@RequestParam String tel) throws Exception{
        return operateService.findByTel(tel);
    }

    //分页
    @GetMapping("/getRecordPage")
    public List<RecordInfoEntity> look(@RequestParam String tel,
                                       @RequestParam int pageNum, @RequestParam int pageSize)
            throws Exception{
        return operateService.findAllByTelAndPage(tel,pageNum,pageSize);
    }


    @DeleteMapping("/delete")
    public void delete(@RequestParam String id) throws Exception {
        //获取需删除的图片路径
        Optional<RecordInfoEntity> results = operateService.findById(id);
        for (PictureInfoEntity result : results.get().getPictureInfoEntities()) {
            String path = result.getPath();
            String groupName = path.substring(0, path.indexOf("/"));
            String remoteFileName = path.substring(path.indexOf("/") + 1);
            //删除图片
            int i = fastDFSClient.deleteFile(groupName, remoteFileName);
            if (i == 0) {
                System.out.println("FastDFS删除文件成功");
            } else {
                System.out.println("FastDFS删除文件失败,图片路径：" + path);
            }
        }
        // 删除mysql内图片表、举报事件表内id相关数据
        operateService.deleteById(id);
    }

}
