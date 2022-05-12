package com.lrr.eduService.service.front;

import com.jfinal.plugin.activerecord.Db;
import com.lrr.eduService.bean.CrmBanner;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.EduTeacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexService {
    private final CrmBanner crmDao = new CrmBanner().dao();
    private final EduCourse courseDao = new EduCourse().dao();
    private final EduTeacher teacherDao = new EduTeacher().dao();
    public List<CrmBanner> findAllBanner(){
        List<CrmBanner> banners = crmDao.findByCache("cacheName","findBanner","select * from crm_banner");//缓存查询
        return banners;
    }
    public Map<String,Object> findIndexCourseAndTeacher(){
        String findHotCourse = Db.getSql("findHotCourse");
        String findHotTeacher = Db.getSql("findHotTeacher");
        List<EduCourse> eduCourses = courseDao.find(findHotCourse);
        List<EduTeacher> eduTeachers = teacherDao.find(findHotTeacher);
        Map<String,Object> map = new HashMap<>();
        map.put("courseItems",eduCourses);
        map.put("teacherItems",eduTeachers);
        return map;
    }
}
