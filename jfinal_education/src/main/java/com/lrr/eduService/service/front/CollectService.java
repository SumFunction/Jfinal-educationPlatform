package com.lrr.eduService.service.front;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lrr.eduService.bean.EduCollect;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.vo.EduCoursePublish;
import com.lrr.eduService.service.after.EduCourseService;

import java.util.*;

public class CollectService{
    private final EduCollect dao = new EduCollect().dao();
    private final EduCourseService eduCourseService = new EduCourseService();
    public boolean addCollect(String userId,String courseId){//添加收藏
        EduCollect eduCollect = new EduCollect();
        eduCollect.setCourseId(courseId);
        eduCollect.setMemberId(userId);
        eduCollect.setId(UUID.randomUUID().toString());
        return eduCollect.save();
    }
    public Map<String, Object> findCollectByPages(String current, String limit, String userId){//分页查询收藏
        List<EduCoursePublish> items = new ArrayList<>();
        Page<Record> paginate = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_collect where memberId = ?", userId);
        List<Record> records = paginate.getList();
        for(Record record : records){
            String courseId = record.get("courseId");
            EduCoursePublish publishCourseInfo = eduCourseService.getPublishCourseInfo(courseId);
            items.add(publishCourseInfo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("items",items);
        map.put("total",paginate.getTotalRow());
        return map;
    }
    public boolean cancelCollect(String userId,String courseId){//删除对应记录
        EduCollect eduCollect = new EduCollect();
        return dao.findFirst("select * from edu_collect where memberId = ? and courseId = ?", userId, courseId).delete();
    }
    public boolean listIfCollect(String userId,String courseId){
        return dao.find("select * from edu_collect where memberId = ? and courseId = ?",userId,courseId).size() > 0 ;
    }
}
