package com.example.springbootfastdfs.Bean;

/**
 * @Author caoerbiao
 * @Date 2020/7/10 19:17
 * @Describe
 */

public class FastDFSFileConfig {
    private String name;
    private byte[] content;
    private String ext; //扩展名
    private String md5;
    private String author;

    public FastDFSFileConfig(String name, byte[] content, String ext){
        this.name = name;
        this.content = content;
        this.ext = ext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
