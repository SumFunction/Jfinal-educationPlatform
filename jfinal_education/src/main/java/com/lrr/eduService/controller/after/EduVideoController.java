package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.EduVideo;
import com.lrr.eduService.service.after.EduVideoService;

@Path("/eduservice/eduVideo")
public class EduVideoController extends Controller {
    private final EduVideoService eduVideoService = new EduVideoService();
    //添加视频小节信息
    public void addVideo(){
        String json = getRawData();
        boolean b = eduVideoService.addVideo(json);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("视频保存失败！"));
    }
    //根据id查询小节信息
    public void findVideoById(){
        String id = get("id");
        EduVideo item = eduVideoService.findVideoById(id);
        renderJson(Result.ok().data("item",item));
    }
    //根据id修改小节信息
    public void updateVideoById(){
        String json = getRawData();
        boolean b = eduVideoService.updateVideById(json);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("视频编辑失败！"));
    }
    //根据id删除视频
    public void deleleVideoById(){
        String id = get("id");
        boolean b = eduVideoService.deleleVideoById(id);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("视频删除失败！"));
    }
}
