package com.lrr.eduService.bean.vo;
/*
封装前端的课程分页查询条件
 */
public class FrontCourseQueryVo {
    private String buyCountSort;
    private String priceSort;
    private String subjectParentId;
    private String subjectId;


    public String getBuyCountSort() {
        return buyCountSort;
    }

    public void setBuyCountSort(String buyCountSort) {
        this.buyCountSort = buyCountSort;
    }

    public String getPriceSort() {
        return priceSort;
    }

    public String getSubjectParentId() {
        return subjectParentId;
    }

    public void setSubjectParentId(String subjectParentId) {
        this.subjectParentId = subjectParentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setPriceSort(String priceSort) {
        this.priceSort = priceSort;
    }
    public FrontCourseQueryVo() {
    }

    public FrontCourseQueryVo(String buyCountSort, String priceSort, String subjectParentId, String subjectId) {
        this.buyCountSort = buyCountSort;
        this.priceSort = priceSort;
        this.subjectParentId = subjectParentId;
        this.subjectId = subjectId;
    }
}
