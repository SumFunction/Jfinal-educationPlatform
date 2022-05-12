package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.subject.OneSubject;
import com.lrr.eduService.service.after.EduSubjectService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Path("/eduservice/after/subject")
public class EduSubjectController extends Controller {
    private final EduSubjectService eduSubjectService = new EduSubjectService();
    //查询课程的所有类别信息
    public void listSubject(){
        List<OneSubject> eduSubjects = eduSubjectService.listSubject();
        renderJson(Result.ok().data("items",eduSubjects));
    }
    //添加一个课程
    public void addSubject(){
        HttpServletRequest request = getRequest();
        if(ServletFileUpload.isMultipartContent(request)){//是否为多段数据
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                List<FileItem> fileItems = servletFileUpload.parseRequest(request);
                for(FileItem fileItem : fileItems){
                    if(!fileItem.isFormField()){
                        //保存excel上传的课程类别
                        eduSubjectService.addSubject(fileItem);
                    }
                }
            } catch (FileUploadException e) {
                System.out.println("课程文件保存失败啦！");
            }
        }



        renderJson(Result.ok());
    }
}
