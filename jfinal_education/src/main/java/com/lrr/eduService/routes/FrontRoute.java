package com.lrr.eduService.routes;

import com.jfinal.config.Routes;
import com.lrr.eduService.validator.front.FrontLoginValidator;

public class FrontRoute extends Routes {
    @Override
    public void config() {
        // 添加前台管理拦截器
        this.addInterceptor(new FrontLoginValidator());
        this.scan("com.lrr.eduService.controller.front");
    }
}
