package com.lrr.eduVod.service;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.utils.StringUtils;
import com.lrr.eduVod.utils.VodUtils;
import org.apache.commons.fileupload.FileItem;

import java.io.IOException;
import java.io.InputStream;

public class AliyunVodService {
    //调用阿里云服务 远程上传视频
    public String uploadVideo(FileItem fileItem){
        try {
            InputStream inputStream = fileItem.getInputStream();
            String originalFilename = fileItem.getName();
            String title = originalFilename.substring(0,
                    originalFilename.lastIndexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(
                    VodUtils.ACCESSKEYID,
                    VodUtils.ACCESSKEYSECRET,
                    title, originalFilename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            String videoId = response.getVideoId();
            if (!response.isSuccess()) {
                if(StringUtils.isEmpty(videoId)){
                    System.out.println("文件上传失败!");
                }
                return null;
            }
            return videoId;
        } catch (IOException e) {
            System.out.println("文件上传失败!");
        }
        return null;
    }
}
