package com.lrr.eduOss.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduOss.service.AliyunOssService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
用于提供图片上传接口的controller
 */
@Path("/eduoss")
public class AliyunOssController extends Controller {
    private final AliyunOssService aliyunOssService = new AliyunOssService();
    //上传到阿里云服务器，返回对应图片url地址
    public void upload(){
        String url = null;
        HttpServletRequest request = getRequest();
        if(ServletFileUpload.isMultipartContent(request)){//是否为多段数据
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                for(FileItem fileItem : fileItems){
                    if(!fileItem.isFormField()){
                        url = aliyunOssService.upload(fileItem);
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

        renderJson(Result.ok().data("url",url));
    }
}
