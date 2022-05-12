package com.lrr.eduService.bean.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/*存放excel的一级列名，二级列名*/
public class EduSubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;

    public String getOneSubjectName() {
        return oneSubjectName;
    }

    public void setOneSubjectName(String oneSubjectName) {
        this.oneSubjectName = oneSubjectName;
    }

    public String getTwoSubjectName() {
        return twoSubjectName;
    }

    public void setTwoSubjectName(String twoSubjectName) {
        this.twoSubjectName = twoSubjectName;
    }

    public EduSubjectData() {
    }

    public EduSubjectData(String oneSubjectName, String twoSubjectName) {
        this.oneSubjectName = oneSubjectName;
        this.twoSubjectName = twoSubjectName;
    }
}
