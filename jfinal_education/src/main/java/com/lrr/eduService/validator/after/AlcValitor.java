package com.lrr.eduService.validator.after;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.PlcMember;
import com.lrr.eduService.service.after.EduLoginService;
import com.lrr.common.token.JwtUtils;

/*
权限验证器，用于验证后端管理员是否有权限获得相应资源
 */
public class AlcValitor extends Validator {
    private final EduLoginService eduLoginService = new EduLoginService();
    //全局配置url，限定哪些url不能被访问
    private final static String authorityUrl[][] = new String[][]{{"/eduservice/after/teacher"},//讲师管理员放行前缀
            {"/eduservice/after/subject","/eduservice/esubject/addSubject"},//分类管理员放行前缀
            {"/eduservice/after/teacher","/eduoss/upload"}//课程管理员放行前缀
    };
    @Override
    protected void validate(Controller c) {
        String requestURI = c.getRequest().getRequestURI();
        if(requestURI.equals("/eduservice/login") || requestURI.equals("/eduservice/info") || requestURI.equals("/"))
            return;
        String token = c.getHeader("X-Token");
        String id = JwtUtils.getMemberIdByJwtToken(token);
        PlcMember info = eduLoginService.info(id);
        Integer auto = info.getAuto();
        if(auto == 0){
            //全局管理员，直接放行
        }else{
            //查找对应的管理员权限看是否有权限
            for(int i = 0;i < authorityUrl[auto-1].length;i++){
                if(requestURI.indexOf(authorityUrl[auto-1][i]) != -1)
                    return;
            }
            //说明没有权限
            addError("mas","用户权限不足");
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderJson(Result.noAuthority());
    }
}
