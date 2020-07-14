package com.example.springbootfastdfs.entity;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;



/**
 * @Author caoerbiao
 * @Date 2020/7/13 20:11
 * @Describe
 */
@Entity
@Table(name = "fdfs")
@GenericGenerator(name = "jpa-uuid",strategy = "uuid")
public class RecordInfoEntity {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String tel;
    private String path;
    private String submittime;
    private String location;
    private String type;
    private String description;

    public RecordInfoEntity() {
    }
    public RecordInfoEntity(String id,String tel,String path,String submittime,String location,String type,String description) {
        this.id = id;
        this.tel = tel;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
