package com.lrr.eduService.controller.front;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.EduTeacher;
import com.lrr.eduService.service.after.EduTeacherService;
import com.lrr.eduService.service.front.FrontTeacherService;

import java.util.List;
import java.util.Map;

@Path("/eduservice/front/teacher")
public class FrontTeacherController extends Controller {
    private final FrontTeacherService frontTeacherService = new FrontTeacherService();
    private final EduTeacherService eduTeacherService = new EduTeacherService();
    //根据讲师id查询它的所有课程详细信息
    public void queryCourseById(){
        String id = get("id");
        List<EduCourse> items = frontTeacherService.queryCourseById(id);
        renderJson(Result.ok().data("items",items));
    }
    //根据老师id查询信息
    public void findById(){
        String id = get("id");
        EduTeacher eduTeacher = eduTeacherService.findById(id);
        renderJson(Result.ok().data("item",eduTeacher));
    }
    public void findTeacherByPages(){
        String current = get("current");
        String limit = get("limit");
        String json = getRawData();//对应表单 post的json信息
        System.out.println(json);
        Map<String, Object> map = eduTeacherService.findTeacherByPages(current, limit, json);
        renderJson(Result.ok().data(map));
    }
}
