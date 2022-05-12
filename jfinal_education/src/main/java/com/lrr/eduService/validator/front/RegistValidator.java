package com.lrr.eduService.validator.front;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.UcenterMember;
import net.sf.json.JSONObject;

/*
该验证器 用来解决注册业务的用户表单提交是否合法
不合法情况：验证发现后台数据库用户名重复，则直接返回false，
合法情况：如果不重复，则交给注册controller处理
 */
public class RegistValidator extends Validator {
    @Override
    protected void validate(Controller c) {
        String json = c.getRawData();
        if("{}".equals(json) || "".equals(json))
            return;
        UcenterMember ucenterMember = (UcenterMember)new JSONObject().toBean(JSONObject.fromObject(json), UcenterMember.class);
        int size = ucenterMember.find("select * from ucenter_member where nickname = ?", ucenterMember.getNickname()).size();
        if(size > 0){
            addError("msg","用户名已经存在!");
        }
    }
    @Override
    protected void handleError(Controller c) {
        c.renderJson(Result.error().message("用户名已经存在"));
    }
}
