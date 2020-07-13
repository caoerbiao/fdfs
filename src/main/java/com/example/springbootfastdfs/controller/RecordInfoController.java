package com.example.springbootfastdfs.controller;

import com.example.springbootfastdfs.entity.RecordInfoEntity;
import com.example.springbootfastdfs.service.FileOperateService;
import com.example.springbootfastdfs.service.OperateRecordTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:58
 * @Describe
 */
@RestController
public class RecordInfoController {
    @Autowired
    private OperateRecordTableService operateService;

    @Autowired
    private FileOperateService fileOperateService;

    @PostMapping("/save")
    public void save(@RequestBody RecordInfoEntity recordInfoEntity, MultipartFile file) throws IOException, SQLException {
        recordInfoEntity.setId(UUID.randomUUID().toString());
        String path = fileOperateService.upload(file);
        operateService.save(recordInfoEntity);
        recordInfoEntity.setPath(path);
    }


}
