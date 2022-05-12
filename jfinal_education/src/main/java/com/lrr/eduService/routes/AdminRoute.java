package com.lrr.eduService.routes;

import com.jfinal.config.Routes;
import com.lrr.eduService.validator.after.AfterValidator;
import com.lrr.eduService.validator.after.AlcValitor;

public class AdminRoute extends Routes {
    @Override
    public void config() {
        // 添加后台管理拦截器，将拦截在此方法中注册的所有 Controller
        this.addInterceptor(new AfterValidator());
        this.addInterceptor(new AlcValitor());//权限验证器
        this.scan("com.lrr.eduService.controller.after");
    }
}
