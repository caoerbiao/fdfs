package com.example.springbootfastdfs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author caoerbiao
 * @Date 2020/7/14 11:39
 * @Describe 随手拍举报相关图片或视频表实例化
 */
@Data
@Entity
@Table(name = "fdfspicture")
public class PictureInfoEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;
//    private String uuid;
    private String path;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "uuid")
    private RecordInfoEntity recordInfoEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RecordInfoEntity getRecordInfoEntity() {
        return recordInfoEntity;
    }

    @JsonBackReference
    public void setRecordInfoEntity(RecordInfoEntity recordInfoEntity) {
        this.recordInfoEntity = recordInfoEntity;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
