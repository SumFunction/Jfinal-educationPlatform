package com.lrr.eduService.controller.front;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.UcenterMember;
import com.lrr.eduService.validator.front.RegistValidator;
import net.sf.json.JSONObject;
import java.util.UUID;
@Path("/eduucenter/front")
public class RegistController extends Controller {
    @Before(RegistValidator.class)//经过合法验证，才能进入controller
    public void regist(){
        //经过上面的验证，一定是合法的注册信息，直接保存到数据库
        String json = getRawData();
        UcenterMember ucenterMember = (UcenterMember)new JSONObject().toBean(JSONObject.fromObject(json), UcenterMember.class);
        ucenterMember.setId(UUID.randomUUID().toString());
        if(ucenterMember.save()){
            renderJson(Result.ok().message("成功注册！"));
            return;
        }
        renderJson(Result.error().message("服务器错误，注册失败，稍后再试！"));
    }
}
