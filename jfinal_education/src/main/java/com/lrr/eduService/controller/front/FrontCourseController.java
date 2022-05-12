package com.lrr.eduService.controller.front;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.common.token.JwtUtils;
import com.lrr.eduService.bean.subject.OneSubject;
import com.lrr.eduService.service.after.EduSubjectService;
import com.lrr.eduService.service.front.FrontCourseService;

import java.util.List;
import java.util.Map;

@Path("/eduservice/front/course")
//处理前端 课程一些信息显示的controller
public class FrontCourseController extends Controller {
    private final FrontCourseService frontCourseService = new FrontCourseService();
    private final EduSubjectService eduSubjectService = new EduSubjectService();
    //前端的课程信息分页查询 带条件的那种
    public void findCourseByPages(){
        String current = get("current");
        String limit = get("limit");
        String json = getRawData();//对应表单 post的json信息
        Map<String, Object> map = frontCourseService.findCourseByPages(current, limit, json);
        renderJson(Result.ok().data(map));
    }
    //前端查询课程的详细信息，需要联合老师的详细信息
    public void findCourseDetailById(){
        String id = get("courseId");
        /*if(getRequest().getHeader("token") != null && JwtUtils.getMemberIdByJwtToken(getRequest().getHeader("token")).equals(get("id")))
            return;//至今没明白为什么重复发送 并且会请求*/
        Map<String, Object> map = frontCourseService.findCourseDetailById(id);
        renderJson(Result.ok().data(map));
    }
    //前端查询类别所有信息
    public void listSubject(){
        List<OneSubject> eduSubjects = eduSubjectService.listSubject();
        renderJson(Result.ok().data("items",eduSubjects));
    }
    //模糊搜索课程
    public void searchByCourseName(){
        String current = get("current");
        String limit = get("limit");
        String title = get("title");
        Map<String, Object> map = frontCourseService.searchCourse(current, limit, title);
        renderJson(Result.ok().data(map));
    }
}
