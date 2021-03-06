package com.gqz.bookcity.utils;

import com.gqz.bookcity.entity.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * <p>fastdfs的工具类</p>
 *
 * @author gqz20
 * @create 2021-10-28 15:37
 **/
public class FastDFSClient {
    static {
        try {
            ClientGlobal.init(new ClassPathResource("fdfs_client.conf").getURL().getPath());
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param file : 要上传的文件信息封装->FastDFSFile
     * @return String[]
     *          1:文件上传所存储的组名
     *          2:文件存储路径
     */
    public static String[] upload(FastDFSFile file){
        //获取文件作者
        NameValuePair[] meta_list = new NameValuePair[2];
        meta_list[0] =new NameValuePair(file.getAuthor());
        meta_list[1] =new NameValuePair(file.getName());

        /*
          文件上传后的返回值
          uploadResults[0]:文件上传所存储的组名，例如:group1
          uploadResults[1]:文件存储路径,例如：M00/00/00/wKjThF0DBzaAP23MAAXz2mMp9oM26.jpeg
         */
        String[] uploadResults = null;
        try {
            //创建TrackerClient客户端对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient对象获取TrackerServer信息
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取StorageClient对象
            StorageClient storageClient = new StorageClient(trackerServer, null);
            //执行文件上传
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadResults;
    }

    /**
     * 获取文件信息
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    public static FileInfo getFile(String groupName, String remoteFileName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获得TrackerServer信息
            TrackerServer trackerServer =trackerClient.getConnection();
            //通过TrackerServer获取StorageClient对象
            StorageClient storageClient = new StorageClient(trackerServer,null);
            //获取文件信息
            return storageClient.get_file_info(groupName,remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws Exception
     */
    public static byte[] downFile(String groupName, String remoteFileName) throws Exception {

        TrackerClient trackerClient = new TrackerClient();
        //4.根据trackerclient获取到链接对象 trackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageServer对象 设置null

        //6.创建stroageClient --->提供了很多的操作图片的API的代码（上传图片，下载 ，删除）
        StorageClient storageClient = new StorageClient(trackerServer, null);

        //7.下在图片
        //参数1 指定要下载的组名
        //参数2 指定要下载的远程文件路径
        byte[] group1s = storageClient.download_file(groupName, remoteFileName);
        return group1s;
    }

    /***
     * 文件删除实现
     * @param groupName:组名
     * @param remoteFileName：文件存储完整名
     */
    public static boolean deleteFile(String groupName, String remoteFileName) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        //4.根据trackerclient获取到链接对象 trackerServer
        TrackerServer trackerServer = trackerClient.getConnection();
        //5.创建storageServer对象 设置null

        //6.创建stroageClient --->提供了很多的操作图片的API的代码（上传图片，下载 ，删除）
        StorageClient storageClient = new StorageClient(trackerServer, null);
        int group1 = storageClient.delete_file(groupName, remoteFileName);
        if (group1 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * 获取组信息
     * @param groupName :组名
     */
    public static StorageServer getStorages(String groupName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //通过trackerClient获取Storage组信息
            return trackerClient.getStoreStorage(trackerServer,groupName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据文件组名和文件存储路径获取Storage服务的IP、端口信息
     * @param groupName :组名
     * @param remoteFileName ：文件存储完整名
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取服务信息
            return trackerClient.getFetchStorages(trackerServer,groupName,remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Tracker服务地址
     */
    public static String getTrackerUrl(){
        try {
            //创建TrackerClient对象
            TrackerClient trackerClient = new TrackerClient();
            //通过TrackerClient获取TrackerServer对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Tracker地址
            return "http://"+trackerServer.getInetSocketAddress().getHostString()+":"+ClientGlobal.getG_tracker_http_port();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
