package com.lrr.eduService.bean.vo;

import com.jfinal.plugin.activerecord.Model;

/*
该类封装 课程课程最终发布信息
 */
public class EduCoursePublish extends Model{
    private String id;//课程id
    private String title;//课程名
    private String cover;//封面
    private String subjectLevelOne;//课程一级分类
    private String subjectLevelTwo;//课程二级分类
    private String teacherName;//老师名称
    private String price;//课程价格
    private String description;//课程描述

    private String avatar;//老师头像
    private String buyCount;//购买量
    private String viewCount;//浏览量

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EduCoursePublish() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EduCoursePublish(String id, String title, String cover, String subjectLevelOne, String subjectLevelTwo, String teacherName, String price, String description, String avatar, String buyCount, String viewCount) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.subjectLevelOne = subjectLevelOne;
        this.subjectLevelTwo = subjectLevelTwo;
        this.teacherName = teacherName;
        this.price = price;
        this.description = description;
        this.avatar = avatar;
        this.buyCount = buyCount;
        this.viewCount = viewCount;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getSubjectLevelOne() {
        return subjectLevelOne;
    }

    public void setSubjectLevelOne(String subjectLevelOne) {
        this.subjectLevelOne = subjectLevelOne;
    }

    public String getSubjectLevelTwo() {
        return subjectLevelTwo;
    }

    public void setSubjectLevelTwo(String subjectLevelTwo) {
        this.subjectLevelTwo = subjectLevelTwo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
