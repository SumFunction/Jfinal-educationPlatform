package com.lrr.eduService.controller.front;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.common.token.JwtUtils;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.service.front.CollectService;

import java.util.List;
import java.util.Map;

@Path("/eduservice/front/collect")
public class CollectController extends Controller {
    private final CollectService collectService = new CollectService();
    public void cancelCollect(){//取消收藏
        String token = getRequest().getHeader("token");
        String courseId = get("courseId");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        boolean b = collectService.cancelCollect(userId, courseId);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("取消失败！"));
    }
    public void findCollectByPages(){//分页查询收藏信息
        String current = get("current");
        String limit = get("limit");
        String token = getRequest().getHeader("token");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        Map<String, Object> collectByPages = collectService.findCollectByPages(current, limit, userId);
        renderJson(Result.ok().data(collectByPages));
    }
    public void addCollect(){//添加收藏
        String token = getRequest().getHeader("token");
        String courseId = get("courseId");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        boolean b = collectService.addCollect(userId, courseId);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("添加失败！"));
    }
    public void listIfCollect(){
        String token = getRequest().getHeader("token");
        String courseId = get("courseId");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        boolean b = collectService.listIfCollect(userId, courseId);
        if(b){
            renderJson(Result.ok().data("item","yes"));
            return;
        }

        renderJson(Result.ok().data("item","no"));
    }
}
