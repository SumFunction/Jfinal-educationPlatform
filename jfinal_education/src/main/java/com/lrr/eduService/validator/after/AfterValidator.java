package com.lrr.eduService.validator.after;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.lrr.common.status.Result;
import com.lrr.common.token.JwtUtils;
/*
后端验证器，用于验证用户是否登录
 */
public class AfterValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        String requestURI = c.getRequest().getRequestURI();
        if(requestURI.equals("/eduservice/login") || requestURI.equals("/"))
            return;
        String token = c.getHeader("X-Token");
        if(token == null || !JwtUtils.checkToken(token)){//合法token
            addError("msg","未登录");
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson(Result.frontNoLogin());
    }
}
