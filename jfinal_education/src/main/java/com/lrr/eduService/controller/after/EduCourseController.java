package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.vo.EduCoursePublish;
import com.lrr.eduService.service.after.EduCourseService;

import java.util.List;
import java.util.Map;

@Path("/eduservice/after/eduCourse")
public class EduCourseController extends Controller {
    private final EduCourseService eduCourseService = new EduCourseService();
    //查询所有课程信息
    public void findAllCourse(){
        List<EduCourse> items = eduCourseService.findAllCourse();
        renderJson(Result.ok().data("items",items));
    }
    //根据课程id查询课程信息
    public void findCourseById(){
        String id = get("id");
        EduCourse item = eduCourseService.findCourseById(id);
        renderJson(Result.ok().data("item",item));
    }
    //分页查询课程，支持条件分页
    public void findCourseByPages(){
        String current = get("current");
        String limit = get("limit");
        String json = getRawData();//对应表单 post的json信息
        Map<String, Object> map = eduCourseService.findCourseByPages(current, limit, json);
        renderJson(Result.ok().data(map));
    }
    //添加课程信息
    public void addCourse(){
        String json = getRawData();
        String id = eduCourseService.addCourse(json);//返回这个课程的id号
        if(id != null){
            renderJson(Result.ok().data("id",id));
            return;
        }
        renderJson(Result.error().message("添加课程失败！"));
    }
    //更新课程信息
    public void updateCourse(){
        String json = getRawData();
        boolean b = eduCourseService.updateCourse(json);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
    //删除根据课程id删除所有课程信息
    public void deleteCourseById(){
        String id = get("id");
        boolean b = eduCourseService.deleteCourseById(id);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
    //查询最终发布课程的信息
    public void getPublishCourseInfo(){
        String id = get("id");
        EduCoursePublish publishCourseInfo = eduCourseService.getPublishCourseInfo(id);
        renderJson(Result.ok().data("item",publishCourseInfo));
    }
    //发布最终课程
    public void publishCourse(){
        String id = get("id");
        boolean b = eduCourseService.publishCourse(id);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
}
