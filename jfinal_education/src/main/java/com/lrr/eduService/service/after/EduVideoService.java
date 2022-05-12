package com.lrr.eduService.service.after;

import com.lrr.eduService.bean.EduChapter;
import com.lrr.eduService.bean.EduTeacher;
import com.lrr.eduService.bean.EduVideo;
import net.sf.json.JSONObject;

import java.util.UUID;

public class EduVideoService {
    private final EduVideo dao = new EduVideo().dao();
    private final EduChapter chapterDao = new EduChapter().dao();
    public boolean addVideo(String json){
        EduVideo eduVideo = (EduVideo) new JSONObject().toBean(JSONObject.fromObject(json), EduVideo.class);
        String chapterId = eduVideo.getChapterId();
        int size = eduVideo.find("select * from edu_video where chapterId = ?", chapterId).size();
        eduVideo.setPrio(size+1);
        eduVideo.setId(UUID.randomUUID().toString());
        return eduVideo.save();
    }
    public EduVideo findVideoById(String id){
        return dao.findById(id);
    }
    public boolean updateVideById(String json){
        EduVideo eduVideo = (EduVideo) new JSONObject().toBean(JSONObject.fromObject(json), EduVideo.class);
        return eduVideo.update();
    }
    public boolean deleleVideoById(String id){
        return dao.deleteById(id);
    }
}
