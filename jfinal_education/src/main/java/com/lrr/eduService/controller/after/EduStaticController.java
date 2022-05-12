package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.eduService.service.after.EduStaticService;

/*
热门销售课程统计
 */
@Path("/eduservice/after/eduStatic")
public class EduStaticController extends Controller {
    private EduStaticService eduStaticService = new EduStaticService();
    public void getCourseSell(){
        renderJson(eduStaticService.getCourseSell());
    }
}
