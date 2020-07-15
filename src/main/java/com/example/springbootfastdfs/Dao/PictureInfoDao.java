package com.example.springbootfastdfs.Dao;

import com.example.springbootfastdfs.entity.PictureInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @Author caoerbiao
 * @Date 2020/7/14 11:58
 * @Describe
 */
public interface PictureInfoDao extends JpaRepository<PictureInfoEntity, String> {
    Optional<PictureInfoEntity> findById(String id);

    @Query(name = "findByUuid",nativeQuery = true,value = "select path from fdfspicture  where uuid= :uuid")
    List<String> findByUuid(String uuid);
}
