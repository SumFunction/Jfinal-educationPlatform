package com.lrr.eduService.controller.after;


import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.EduTeacher;
import com.lrr.eduService.service.after.EduTeacherService;

import java.util.List;
import java.util.Map;

/*
该控制器类,用于解决用户获得讲师相关信息的接口
 */
@Path("/eduservice/after/teacher")
public class EduTeacherController extends Controller {
    private final EduTeacherService eduTeacherService = new EduTeacherService();
    //查询所有讲师信息
    public void findAll(){
        List<EduTeacher> all = eduTeacherService.findAll();
        renderJson(Result.ok().data("items",all));
    }
    //根据讲师id查询讲师信息
    public void findById(){
        String id = get("id");
        EduTeacher eduTeacher = eduTeacherService.findById(id);
        renderJson(Result.ok().data("item",eduTeacher));
    }
    //根据讲师id删除讲师信息
    public void deleteTeacherById(){
        String id = get("id");
        boolean b = eduTeacherService.deleteTeacherById(id);
        renderJson(Result.ok());
    }
    /*根据页码查询讲师信息
    支持带条件的分页查询 也就是说 查询条件类可以有也可以没有
    */

    public void findTeacherByPages(){
        String current = get("current");
        String limit = get("limit");
        String json = getRawData();//对应表单 post的json信息
        System.out.println(json);
        Map<String, Object> map = eduTeacherService.findTeacherByPages(current, limit, json);
        renderJson(Result.ok().data(map));
    }
    //插入一个讲师信息
    public void addTeacher(){
        String json = getRawData();
        boolean b = eduTeacherService.addTeacher(json);
        if (b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
    //修改一个讲师信息
    public void updateTeacher(){
        String data = getRawData();
        boolean b = eduTeacherService.updateTeacher(data);
        if (b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
}
