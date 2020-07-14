package com.example.springbootfastdfs.service;

import com.example.springbootfastdfs.Dao.PictureInfoDao;
import com.example.springbootfastdfs.entity.PictureInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author caoerbiao
 * @Date 2020/7/14 11:59
 * @Describe
 */
@Service
public class PictureInfoService {
    @Autowired
    private PictureInfoDao pictureInfoDao;

    public PictureInfoEntity save(PictureInfoEntity pictureInfoEntity) {
        return pictureInfoDao.save(pictureInfoEntity);
    }

}
