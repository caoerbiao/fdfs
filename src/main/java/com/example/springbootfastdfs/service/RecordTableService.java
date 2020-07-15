package com.example.springbootfastdfs.service;

import com.example.springbootfastdfs.Bean.RecordInfoBean;
import com.example.springbootfastdfs.Bean.Result;
import com.example.springbootfastdfs.Dao.RecordInfoDao;

import com.example.springbootfastdfs.entity.PictureInfoEntity;
import com.example.springbootfastdfs.entity.RecordInfoEntity;
import com.example.springbootfastdfs.until.ConvertUtils;
import com.sun.org.apache.bcel.internal.generic.ARETURN;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:46
 * @Describe
 */
@Service
public class RecordTableService extends ConvertUtils {
    @Autowired
    private RecordInfoDao recordInfoDao;
    @Autowired
    private PictureInfoService pictureInfoService;

    public Optional<RecordInfoEntity> findById(String id) {
        return recordInfoDao.findById(id);
    }

    public void deleteById(String id) {
        recordInfoDao.deleteById(id);
    }

    public RecordInfoEntity save(RecordInfoEntity recordInfoEntity) {
        return recordInfoDao.save(recordInfoEntity);
    }

    // 未分页
    public List<RecordInfoEntity> findByTel(String tel){
        List<RecordInfoEntity> recordList = recordInfoDao.findByTel(tel);
        for (RecordInfoEntity record:recordList){
            List<PictureInfoEntity> picPathList = record.getPictureInfoEntities();
            for (PictureInfoEntity picPath:picPathList){
                picPath.setPath("http://192.168.148.120:8888/"+picPath.getPath());
            }
        }
    return recordList;
    }

    //分页
    public List<RecordInfoEntity> findAllByTelAndPage(String tel, int pageNum, int pageSize) {
        Result resultAll = new Result();
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Specification<RecordInfoEntity> spec = new Specification<RecordInfoEntity>() {
            @Override
            public Predicate toPredicate(@NotNull Root<RecordInfoEntity> root, CriteriaQuery<?> criteriaQuery, @NotNull CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("tel"), tel);
            }
        };
        Page<RecordInfoEntity> pageList = recordInfoDao.findAll(spec, pageable);
        List<RecordInfoEntity> listList = pageList.getContent();
        for (RecordInfoEntity list:listList){
            List<PictureInfoEntity> picPathList = list.getPictureInfoEntities();
            for (PictureInfoEntity picPath:picPathList){
                picPath.setPath("http://192.168.148.120:8888/"+picPath.getPath());
            }
        }
        return listList;
    }

}
