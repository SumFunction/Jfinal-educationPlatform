package com.lrr.eduService.controller.front;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.CrmBanner;
import com.lrr.eduService.service.front.IndexService;

import java.util.List;
import java.util.Map;

//首页controller，用于返回一些首页的信息
@Path("/eduservice/front")
public class IndexController extends Controller {
    private final IndexService indexService = new IndexService();
    //获取首页轮播图信息
    public void findAllBanner(){
        List<CrmBanner> items = indexService.findAllBanner();
        renderJson(Result.ok().data("items",items));
    }
    //获取首页所有课程以及教师信息
    public void findIndexCourseAndTeacher(){
        Map<String, Object> indexCourseAndTeacher = indexService.findIndexCourseAndTeacher();
        renderJson(Result.ok().data(indexCourseAndTeacher));
    }
}
