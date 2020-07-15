package com.example.springbootfastdfs.client;

import com.example.springbootfastdfs.Bean.FastDFSFileConfig;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author caoerbiao
 * @Date 2020/7/10 19:24
 * @Describe
 */
@Component
public class FastDFSClient {
    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageClient storageClient;
    private static StorageServer storageServer;

    //通过静态代码块初始化storageServer
    static {
        try {
            String filepath = new ClassPathResource("fdfs_clinet.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filepath);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("FastDFS Client is missing configuration file!",e);
        } catch (MyException e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }

    /*
    * 上传
    * */
    public String[] upload(FastDFSFileConfig file){
        logger.info("FileName:" +file.getName() + " FileLength:" + file.getContent().length);
        Long startTime = System.currentTimeMillis();
        NameValuePair [] mate_list = new NameValuePair[1];
        mate_list[0] = new NameValuePair("author",file.getAuthor());
        String[] uploadResults = null;
        try{
           storageClient = new StorageClient(trackerServer,storageServer);
           //mate_list指定为null，元数据未保存，
           uploadResults = storageClient.upload_file(file.getContent(),file.getExt(),null);
        } catch (IOException e) {
           logger.error("IOException when upload the file:" +file.getName(),e);
        } catch (Exception e) {
            logger.error("NO IOException when upload the file:" +file.getName(),e);
        }
        if (uploadResults == null){
            logger.error("upload fail! errorCode:" + storageClient.getErrorCode());
        }
        logger.info("Upload spead time:" +(System.currentTimeMillis()-startTime) + " ms");
        return uploadResults;
    }

    /**
     * 下载文件
     *
     * @param response
     * @param group 文件所在的组 如：group1
     * @param filepath 数据库存的文件路径: M00/开头的文件路径
     * @throws IOException
     */
    public  byte[] download(HttpServletResponse response, String group, String filepath) {
        byte[] content = null;
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            content = storageClient.download_file(group, filepath);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.getWriter().write("Error : file not Found!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (storageServer != null)
                    storageServer.close();
                if (trackerServer != null)
                    trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 从FastDFS删除文件
     * @param groupName      文件在FastDFS中的组名: group1
     * @param remoteFilename 文件在FastDFS中的名称: M00/00/00/wKiUeF8IacmAfR60AA_iASvO3LA641.jpg
     */
    public Integer deleteFile(String groupName, String remoteFilename) {
        int i = 0;
        try {
            storageClient = new StorageClient(trackerServer,storageServer);
            i = storageClient.delete_file(groupName, remoteFilename);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        } finally {
            try {
                if (storageServer != null)
                    storageServer.close();
                if (trackerServer != null)
                    trackerServer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    /**
     * 获取文件信息
     * @param groupName  文件所在组名：group1
     * @param remoteFileName  文件的存储路径名 M00/00/00/wKghhV6FkViAfTuRAAGSbLHFVsM382.png
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) throws Exception {
        storageClient = new StorageClient(trackerServer,storageServer);
        return storageClient.get_file_info(groupName, remoteFileName);
    }

    //获取tracker服务器ip
    public static String getTrackerUrl(){
        String ip = trackerServer.getInetSocketAddress().getHostString();
        int port = ClientGlobal.getG_tracker_http_port();
        return "http://"+ip+":"+port;
    }

    //获取Storage服务器ip
    public static String getStorageUrl(){
        String ip = storageServer.getInetSocketAddress().getHostString();
        String port = "8888";
        return "http://"+ip+":"+port;
    }
}


