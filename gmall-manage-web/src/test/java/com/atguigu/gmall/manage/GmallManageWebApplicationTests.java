package com.atguigu.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() {
        //配置fdfs全局信息
        String file = GmallManageWebApplicationTests.class.getClassLoader().getResource("").getFile();
        try {
            ClientGlobal.init(file);
            //获得tracker
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer connection = trackerClient.getConnection();
            //通过tracker获得storage
            StorageClient storageClient = new StorageClient(connection, null);
            //通过storage上传文件
            String[] gifs = storageClient.upload_file("d:/a.gif", "gif", null);
            String url = "http://192.168.199.237";
            for (String gif : gifs) {
                url = url + "/" + gif;
            }

            System.out.println(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}
