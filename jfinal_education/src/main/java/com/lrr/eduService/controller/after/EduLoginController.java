package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.PlcMember;
import com.lrr.eduService.service.after.EduLoginService;
import com.lrr.common.token.JwtUtils;

/*
前端登录控制器
 */
@Path("/eduservice")
public class EduLoginController extends Controller {
    private final EduLoginService eduLoginService = new EduLoginService();
    public void login(){
        String json = getRawData();
        String token = eduLoginService.login(json);
        if (token != null){
            renderJson(Result.ok().data("token",token));
            return;
        }
        renderJson(Result.error().message("账号或者密码错误！"));
    }
    public void info(){
        String token = get("token");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        PlcMember info = eduLoginService.info(userId);
        renderJson(Result.ok().data("user",info));
    }
}
