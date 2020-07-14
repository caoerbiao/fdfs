package com.example.springbootfastdfs.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:11
 * @Describe 随手拍举报信息
 */
@Entity
@Table(name = "fdfs")
@GenericGenerator(name = "jpa-uuid",strategy = "uuid")
public class RecordInfoEntity {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String tel;
    private String submittime;
    private String location;
    private String type;
    private String description;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "recordInfoEntity")
    private List<PictureInfoEntity> pictureInfoEntities = new ArrayList<>();

    public List<PictureInfoEntity> getPictureInfoEntities() {
        return pictureInfoEntities;
    }

    @JsonBackReference
    public void setPictureInfoEntities(List<PictureInfoEntity> pictureInfoEntities) {
        this.pictureInfoEntities = pictureInfoEntities;
    }

    public RecordInfoEntity() {
    }

    public RecordInfoEntity(String id,String tel,String path,String submittime,String location,String type,String description) {
        this.id = id;
        this.tel = tel;
        this.submittime = submittime;
        this.location = location;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSubmittime() {
        return submittime;
    }

    public void setSubmittime(String submittime) {
        this.submittime = submittime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
