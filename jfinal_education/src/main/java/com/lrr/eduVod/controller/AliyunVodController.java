package com.lrr.eduVod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.service.after.EduCourseService;
import com.lrr.eduVod.service.AliyunVodService;
import com.lrr.eduVod.utils.AliyunVodSDKUtils;
import com.lrr.eduVod.utils.VodUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Path("/eduvod")
public class AliyunVodController extends Controller {
    private final AliyunVodService aliyunVodService = new AliyunVodService();
    //上传视频，返回视频的id号 即resourceid
    public void uploadVideo(){
        String resourceId = null;
        HttpServletRequest request = getRequest();
        if(ServletFileUpload.isMultipartContent(request)){//是否为多段数据
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                for(FileItem fileItem : fileItems){
                    if(!fileItem.isFormField()){
                        resourceId = aliyunVodService.uploadVideo(fileItem);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
        renderJson(Result.ok().data("videoId",resourceId));
    }
    //根据视频id获取播放凭证，只有这个播放凭证，视频才能播放
    public void getAuth(){
        String id = get("id");
        //获取阿里云存储相关常量
        String accessKeyId = VodUtils.ACCESSKEYID;
        String accessKeySecret = VodUtils.ACCESSKEYSECRET;
        //初始化
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId,
                accessKeySecret);
        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(id);
        //响应
        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            //得到播放凭证
            String playAuth = response.getPlayAuth();
            //返回结果
            renderJson(Result.ok().message("获取凭证成功").data("playAuth", playAuth));
        }catch (Exception e){
            System.out.println("获取凭证失败！");
        }
    }
}
