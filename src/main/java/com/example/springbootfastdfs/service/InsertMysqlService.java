package com.example.springbootfastdfs.service;

import com.example.springbootfastdfs.config.MysqlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author caoerbiao
 * @Date 2020/7/13 10:32
 * @Describe  将文件的path存入mysql
 */
@Service
public class InsertMysqlService {

    @Autowired
    private MysqlConfig mysqlConfig;


    public  void  insertMysql(String path) throws SQLException {
        String id = String.valueOf(System.currentTimeMillis());
        Connection connection =null;
        Statement statement =null;
        String tar = "Failed";
        try{
            connection = DriverManager.getConnection(mysqlConfig.getUrl(),mysqlConfig.getUsername(),mysqlConfig.getPassword());
            String sql = "Insert into fdfs(id, path) VALUES ('"+ id+ "','" +path + "')";
            statement = connection.createStatement();
            System.out.println(sql);
            statement.executeUpdate(sql);
            tar = "sucess";

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if(statement!=null) {
                    statement.close();
                }
                if(connection!=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

