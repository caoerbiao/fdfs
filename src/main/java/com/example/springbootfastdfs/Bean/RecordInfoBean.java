package com.example.springbootfastdfs.Bean;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * @Author caoerbiao
 * @Date 2020/7/15 11:28
 * @Describe
 */

public class RecordInfoBean implements Serializable {
    private String id;
    private String tel;
    private String submittime;
    private String location;
    private String type;
    private String description;
    private String path;


    @Override
    public String toString() {
        return "RecordInfoBean{" +
                "id='" + id + '\'' +
                ", tel='" + tel + '\'' +
                ", submittime='" + submittime + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", path='" + path + '\'' +
                '}';
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
