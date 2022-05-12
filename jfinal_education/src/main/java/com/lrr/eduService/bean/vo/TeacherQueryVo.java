package com.lrr.eduService.bean.vo;
/*
保存教师页面查询条件
 */
public class TeacherQueryVo {
    private String name;
    private String level;

    public TeacherQueryVo(String name, String level) {
        this.name = name;
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public TeacherQueryVo() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
