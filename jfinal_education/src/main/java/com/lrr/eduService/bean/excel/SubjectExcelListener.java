package com.lrr.eduService.bean.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lrr.eduService.bean.EduSubject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SubjectExcelListener extends AnalysisEventListener<EduSubjectData> {
    private final EduSubject eduSubject = new EduSubject();
    public SubjectExcelListener() {
    }
    @Override
    public void invoke(EduSubjectData eduSubjectData, AnalysisContext analysisContext) {
        String oneSubjectName = eduSubjectData.getOneSubjectName();
        EduSubject eduSubject = exitSubject(oneSubjectName, "0");

        if(eduSubject == null){//如果新添加的一级分类没存在于数据库 则加入这个一级分类
            eduSubject = new EduSubject();
            eduSubject.setTitle(oneSubjectName);
            eduSubject.setParentId("0");
            String id = UUID.randomUUID().toString();
            eduSubject.setId(id);
            eduSubject.save();
        }
        String twoSubjectName = eduSubjectData.getTwoSubjectName();
        EduSubject twoEduSubject;
        twoEduSubject = exitSubject(twoSubjectName,eduSubject.getId());//查询二级分类是否重复
        if(twoEduSubject == null){
            twoEduSubject = new EduSubject();
            twoEduSubject.setTitle(twoSubjectName);
            twoEduSubject.setParentId(eduSubject.getId());
            String id = UUID.randomUUID().toString();
            twoEduSubject.setId(id);
            twoEduSubject.save();
        }
    }
    //判断是否一级类别已经存在
    public EduSubject exitSubject(String subjectName,String pid){
        List<EduSubject> eduSubjects = eduSubject.find("select * from edu_subject where title=? and parentId=?", subjectName, pid);
        if(eduSubjects.size() > 0)
            return eduSubjects.get(0);
        return null;
    }
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
