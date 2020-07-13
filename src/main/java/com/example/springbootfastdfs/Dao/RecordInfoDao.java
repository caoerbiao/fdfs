package com.example.springbootfastdfs.Dao;

import com.example.springbootfastdfs.entity.RecordInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:41
 * @Describe
 */
public interface RecordInfoDao extends JpaRepository<RecordInfoEntity,String> {

}
