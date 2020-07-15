package com.example.springbootfastdfs.Dao;

import com.example.springbootfastdfs.Bean.RecordInfoBean;
import com.example.springbootfastdfs.entity.RecordInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;
import java.util.Optional;

/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:41
 * @Describe
 */

public interface RecordInfoDao extends JpaRepository<RecordInfoEntity, String>, JpaSpecificationExecutor<RecordInfoEntity> {

    Optional<RecordInfoEntity> findById(String id);
    void deleteById(String id);

    List<RecordInfoEntity> findByTel(String tel);

}
