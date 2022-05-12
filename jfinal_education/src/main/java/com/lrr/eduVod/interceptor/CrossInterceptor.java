package com.lrr.eduVod.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import javax.servlet.http.HttpServletResponse;

/*
处理跨域拦截器
 */
public class CrossInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {

        addCorsHead(inv.getController().getResponse());
        String method = inv.getController().getRequest().getMethod();
        if ("OPTIONS".equals(method)){//OPTIONS预请求 不做处理
            inv.getController().renderJson();
        }else{
            inv.invoke();
        }
        System.out.println(method);
    }
    private void addCorsHead(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        //设置响应头 允许 x-token
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, accessToken, token, x-token");
        /*response.setHeader(
                "Access-Control-Allow-Headers",
                "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");*/
    }
}
