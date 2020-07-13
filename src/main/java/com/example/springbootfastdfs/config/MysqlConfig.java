package com.example.springbootfastdfs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author caoerbiao
 * @Date 2020/7/13 14:59
 * @Describe 定义mysql的配置类
 */

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class MysqlConfig {

    private  String url;
    private  String username;
    private  String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
