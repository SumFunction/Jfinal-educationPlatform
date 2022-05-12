package com.lrr.eduService.validator.front;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.lrr.common.status.Result;

/*
该验证器验证前台用户是否已经登录或携带的token是否合法，对于需要登录的url 不予返回信息，返回给前台无登录消息
 */
public class FrontLoginValidator extends Validator {
    private static final String privateUrl[] = new String[]{
            "/eduservice/front/order/createOrder",
            "/eduservice/front/collect"
    };
    @Override
    protected void validate(Controller c) {
        String requestURI = c.getRequest().getRequestURI();
        boolean flag = true;
        for(int i = 0;i < privateUrl.length;i++){
            //代表必须要登陆
            if(requestURI.indexOf(privateUrl[i])!= -1 && c.getRequest().getHeader("token") == null){
                addError("msg","必须要登录");
            }
        }
    }
    @Override
    protected void handleError(Controller c) {
        c.renderJson(Result.frontNoLogin());
    }
}
