package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.PlcMember;
import com.lrr.eduService.service.after.EduPlcService;

import java.util.List;

@Path("/eduservice/after/authrity")
public class EduPlcController extends Controller {
    private final EduPlcService eduPlcService = new EduPlcService();
    public void getAllMember(){//查询所有后台人员信息
        List<PlcMember> allMember = eduPlcService.getAllMember();
        renderJson(Result.ok().data("items",allMember));
    }
    public void deleteMemberById(){//删除某个后台人员根据id
        String id = get("id");
        boolean b = eduPlcService.deleteMemberById(id);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
    public void updateMember(){//更新某个后台人员信息
        String json = getRawData();
        boolean b = eduPlcService.updateMember(json);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
    public void getMemberInfoById(){//根据id获取信息
        String id = get("id");
        PlcMember memberInfoById = eduPlcService.getMemberInfoById(id);
        renderJson(Result.ok().data("item",memberInfoById));
    }
    public void addMember(){//添加某个成员信息
        String json = getRawData();
        boolean b = eduPlcService.addMember(json);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error());
    }
    public void findByPages(){
        String current = get("current");
        String limit = get("limit");
        String aut = get("aut");
        List<PlcMember> byPages = eduPlcService.findByPages(current, limit, aut);
        renderJson(Result.ok().data("items",byPages));
    }
}
