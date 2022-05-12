package com.lrr.eduOss.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.lrr.eduOss.utils.OssUtils;
import org.apache.commons.fileupload.FileItem;

import java.util.UUID;

public class AliyunOssService {
    //将文件的inputstream上传到阿里云服务器，返回对应的url
    public String upload(FileItem file) {
        String endpoing = OssUtils.ENDPOING;
        String accesskeyid = OssUtils.ACCESSKEYID;
        String accesskeysecret = OssUtils.ACCESSKEYSECRET;
        String bucketname = OssUtils.BUCKETNAME;
        String fileName = UUID.randomUUID().toString()+file.getName();
        OSS ossClient = new OSSClientBuilder().build(OssUtils.ENDPOING, OssUtils.ACCESSKEYID, OssUtils.ACCESSKEYSECRET);
        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketname, fileName, file.getInputStream());
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "https://"+bucketname+"."+endpoing + "/" + fileName;
    }
}
