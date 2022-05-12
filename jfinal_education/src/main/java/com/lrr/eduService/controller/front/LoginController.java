package com.lrr.eduService.controller.front;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.UcenterMember;
import com.lrr.eduService.service.front.LoginService;
import com.lrr.common.token.JwtUtils;

@Path("/eduucenter/front")
/*
前端登录controller 以及获取用户信息
 */
public class LoginController extends Controller {
    private final LoginService loginService = new LoginService();
    public void login(){
        String json = getRawData();
        String token = loginService.login(json);
        if (token != null){
            renderJson(Result.ok().data("token",token));
            return;
        }
        renderJson(Result.error().message("账号或者密码错误！"));
    }
    //根据token获取用户信息
    public void getUserInfo(){
        String token = getRequest().getHeader("token");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        UcenterMember userInfo = loginService.getUserInfo(userId);
        renderJson(Result.ok().data("user",userInfo));
    }

}
