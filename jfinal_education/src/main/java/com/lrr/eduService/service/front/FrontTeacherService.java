package com.lrr.eduService.service.front;

import com.lrr.eduService.bean.EduCourse;

import java.util.List;

public class FrontTeacherService {
    private final EduCourse dao = new EduCourse().dao();
    public List<EduCourse> queryCourseById(String id){
        List<EduCourse> eduCourses = dao.find("select * from edu_course where teacherId = ?", id);
        return eduCourses;
    }
}
