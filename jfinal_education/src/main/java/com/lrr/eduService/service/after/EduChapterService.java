package com.lrr.eduService.service.after;

import com.jfinal.plugin.activerecord.Db;
import com.lrr.eduService.bean.EduChapter;
import com.lrr.eduService.bean.EduTeacher;
import com.lrr.eduService.bean.EduVideo;
import com.lrr.eduService.bean.chapter.ChapterData;
import com.lrr.eduService.bean.chapter.VideoData;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EduChapterService {
    private final EduChapter chapterDao = new EduChapter().dao();
    private final EduVideo videoDao = new EduVideo().dao();
    public List<ChapterData> findAllChapterAndVideo(String id){
        List<EduChapter> eduChapters = chapterDao.find("select * from edu_chapter where courseId = ? order by prio ASC", id);
        List<EduVideo> eduVideos = videoDao.find("select * from edu_video where courseId = ? order by prio ASC", id);
        List<ChapterData> items = new ArrayList<>();//返回封装结果 跟Subject一样原理
        for(int i = 0;i < eduChapters.size();i++){
            EduChapter eduChapterItem = eduChapters.get(i);
            ChapterData chapterData = new ChapterData();
            chapterData.setId(eduChapterItem.getId());
            chapterData.setTitle(eduChapterItem.getTitle());
            for(int j = 0;j < eduVideos.size();j++){
                EduVideo eduVideoItem = eduVideos.get(j);
                if(eduVideoItem.getChapterId().equals(eduChapterItem.getId())){
                    VideoData videoData = new VideoData();
                    videoData.setId(eduVideoItem.getId());
                    videoData.setTitle(eduVideoItem.getTitle());
                    videoData.setVideoSourceId(eduVideoItem.getVideoSourceId());
                    videoData.setIsFree(eduVideoItem.getIsFree());
                    chapterData.getChildren().add(videoData);
                }
            }
            items.add(chapterData);
        }
        return items;
    }
    public EduChapter getChapterInfoById(String id){
        return chapterDao.findById(id);
    }
    public boolean addChapter(String json){
        EduChapter eduChapter = (EduChapter) new JSONObject().toBean(JSONObject.fromObject(json), EduChapter.class);
        String courseId = eduChapter.getCourseId();
        int size = chapterDao.find("select * from edu_chapter where courseId = ?", courseId).size();
        eduChapter.setPrio(size+1);
        eduChapter.setId(UUID.randomUUID().toString());
        return eduChapter.save();
    }
    public boolean updateChapter(String json){
        EduChapter eduChapter = (EduChapter) new JSONObject().toBean(JSONObject.fromObject(json), EduChapter.class);
        return eduChapter.update();
    }
    public boolean deleteChapterById(String id){
        int size = videoDao.find("select * from edu_video where chapterId=?", id).size();
        if(size > 0)
            return false;
        return chapterDao.deleteById(id);
    }
}
