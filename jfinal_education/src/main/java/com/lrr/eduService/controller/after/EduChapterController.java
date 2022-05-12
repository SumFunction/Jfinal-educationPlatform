package com.lrr.eduService.controller.after;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.EduChapter;
import com.lrr.eduService.bean.chapter.ChapterData;
import com.lrr.eduService.service.after.EduChapterService;

import java.util.List;

@Path("/eduservice/after/eduChapter")
public class EduChapterController extends Controller {
    private final EduChapterService eduChapterService = new EduChapterService();
    //查询根据课程id查询所有章节及其视频信息
        public void findAllChapterAndVideoById(){
        String id = get("id");
        List<ChapterData> items = eduChapterService.findAllChapterAndVideo(id);
        renderJson(Result.ok().data("items",items));
    }
    public void getChapterInfoById(){
        String id = get("id");
        EduChapter item = eduChapterService.getChapterInfoById(id);
        renderJson(Result.ok().data("item",item));
    }
    //添加章节
    public void addChapter(){
        String json = getRawData();
        boolean b = eduChapterService.addChapter(json);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("添加章节失败！"));
    }
    //更新章节
    public void updateChapter(){
        String json = getRawData();
        boolean b = eduChapterService.updateChapter(json);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("更新章节失败！"));
    }
    //删除章节
    public void deleteChapterById(){
        String id = get("id");
        boolean b = eduChapterService.deleteChapterById(id);
        if(b){
            renderJson(Result.ok());
            return;
        }
        renderJson(Result.error().message("删除章节失败！请先删除对应小节"));
    }
}
