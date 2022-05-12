package com.lrr.eduService.service.after;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lrr.eduService.bean.EduChapter;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.EduTeacher;
import com.lrr.eduService.bean.EduVideo;
import com.lrr.eduService.bean.vo.CourseQueryVo;
import com.lrr.eduService.bean.vo.EduCoursePublish;
import net.sf.json.JSONObject;

import java.util.*;

public class EduCourseService {
    private final EduTeacher daoT = new EduTeacher().dao();
    private final EduCourse dao = new EduCourse().dao();
    public List<EduCourse> findAllCourse(){
        List<EduCourse> all = dao.findAll();
        return all;
    }
    public EduCourse findCourseById(String id){
        EduCourse eduCourse = dao.findById(id);
        return eduCourse;
    }
    public Map<String,Object> findCourseByPages(String current,String limit,String json){
        Page<Record> data = null;
        if ("{}".equals(json) || "".equals(json)){
            data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course");
        }else{
            CourseQueryVo condition  = (CourseQueryVo)new JSONObject().toBean(JSONObject.fromObject(json), CourseQueryVo.class);
            String title = condition.getTitle();
            String status = condition.getStatus();
            if (title == null && status != null)
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course where status=?",status);
            if(status == null && title != null)
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course where title=?",title);
            if(status!=null && title!= null)
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_course where status=? and title=?",status,title);
        }
        //封装分页数据
        ArrayList<EduCourse> eduCourses = new ArrayList<>();
        List<Record> list = data.getList();
        for(Record record : list){
            EduCourse eduCourse = new EduCourse();
            eduCourse.setId((String) record.get("id"));
            eduCourse.setTitle((String) record.get("title"));
            eduCourse.setStatus((String) record.get("status"));
            eduCourse.setPrice(record.getBigDecimal("price"));
            eduCourse.setBuyCount(record.getBigInteger("buyCount"));
            eduCourse.setDescription((String) record.get("description"));
            eduCourse.setTeacherId(daoT.findById(record.get("teacherId")).getName());
            eduCourse.setCover((String) record.get("cover"));
            eduCourses.add(eduCourse);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("items",eduCourses);
        map.put("total",data.getTotalRow());
        return map;
    }
    public String addCourse(String json){
        EduCourse eduCourse = (EduCourse)new JSONObject().toBean(JSONObject.fromObject(json), EduCourse.class);
        String id = UUID.randomUUID().toString();
        eduCourse.setId(id);
        if(eduCourse.save())
            return id;
        return null;
    }
    public boolean updateCourse(String json){
        EduCourse eduCourse = (EduCourse)new JSONObject().toBean(JSONObject.fromObject(json), EduCourse.class);
        return eduCourse.update();
    }
    public boolean deleteCourseById(String id){
        boolean b = dao.deleteById(id);
        List<EduChapter> eduChapters = new EduChapter().find("select * from edu_chapter where courseId = ?", id);
        for(EduChapter eduChapter : eduChapters)
            eduChapter.delete();
        List<EduVideo> eduVideos = new EduVideo().find("select * from edu_video where courseId = ?", id);
        for(EduVideo eduVideo : eduVideos)
            eduVideo.delete();
        return b;
    }
    //该方法 要得到这节课的老师信息 小节信息 以及课程本身信息 涉及三张表信息查询
    public EduCoursePublish getPublishCourseInfo(String id){
        String sql = Db.getSql("findCoursePublishInfo");
        Record first = Db.findFirst(sql, id);

        EduCoursePublish eduCoursePublish = new EduCoursePublish();

        eduCoursePublish = (EduCoursePublish) eduCoursePublish.put(first);
        /*eduCoursePublish.setId((String) first.get("id"));
        eduCoursePublish.setCover((String) first.get("cover"));
        eduCoursePublish.setDescription((String) first.get("description"));
        eduCoursePublish.setPrice(first.getBigDecimal("price").toString());
        eduCoursePublish.setSubjectLevelOne((String) first.get("subjectLevelOne"));
        eduCoursePublish.setSubjectLevelTwo((String) first.get("subjectLevelTwo"));
        eduCoursePublish.setTitle((String) first.get("title"));
        eduCoursePublish.setTeacherName((String) first.get("teacherName"));
        eduCoursePublish.setAvatar((String) first.get("avatar"));
        eduCoursePublish.setBuyCount(first.getBigInteger("buyCount").toString());
        eduCoursePublish.setViewCount(first.getBigInteger("viewCount").toString());*/
        return eduCoursePublish;
    }
    public boolean publishCourse(String id){
        EduCourse eduCourse = dao.findById(id);
        eduCourse.setStatus("Normal");
        return eduCourse.update();
    }
}
