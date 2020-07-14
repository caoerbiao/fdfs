package com.example.springbootfastdfs.service;

import com.example.springbootfastdfs.Dao.RecordInfoDao;

import com.example.springbootfastdfs.entity.RecordInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:46
 * @Describe
 */
@Service
public class OperateRecordTableService {
    @Autowired
    private RecordInfoDao recordInfoDao;


    public Optional<RecordInfoEntity> findById(String id) {
        return recordInfoDao.findById(id);
    }

    public void delete(String id){

        recordInfoDao.deleteById(id);
    }

    public  RecordInfoEntity save(RecordInfoEntity recordInfoEntity) {

        return recordInfoDao.save(recordInfoEntity);
    }

}
