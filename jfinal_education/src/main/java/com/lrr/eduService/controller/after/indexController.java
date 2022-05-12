package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

@Path("/")
/*
后台管理系统跳转controller
 */
public class indexController extends Controller {
    public void index(){
        render("index.html");
    }
}
