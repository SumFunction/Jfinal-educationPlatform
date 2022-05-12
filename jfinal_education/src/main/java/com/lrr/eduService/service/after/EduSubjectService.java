package com.lrr.eduService.service.after;

import com.alibaba.excel.EasyExcel;
import com.lrr.eduService.bean.EduSubject;
import com.lrr.eduService.bean.excel.EduSubjectData;
import com.lrr.eduService.bean.excel.SubjectExcelListener;
import com.lrr.eduService.bean.subject.OneSubject;
import com.lrr.eduService.bean.subject.TwoSubject;
import org.apache.commons.fileupload.FileItem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EduSubjectService {
    private final EduSubject dao = new EduSubject().dao();
    public List<OneSubject> listSubject(){
        List<EduSubject> oneSubjectList = dao.find("select * from edu_subject where parentId = '0' ");//找出所有一级分类
        List<EduSubject> twoSubjectList = dao.find("select * from edu_subject where parentId != '0' ");//找出所有二级分类
        List<OneSubject> items = new ArrayList<OneSubject>();//封装查询结果
        for(int i = 0;i < oneSubjectList.size();i++){
            EduSubject oneSubjectItem = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();

            oneSubject.setId(oneSubjectItem.getId());
            oneSubject.setTitle(oneSubjectItem.getTitle());

            List<TwoSubject> children = new ArrayList<>();
            for(int j = 0;j < twoSubjectList.size();j++){
                EduSubject twoSubjectItem = twoSubjectList.get(j);
                if(twoSubjectItem.getParentId().equals(oneSubjectItem.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(twoSubjectItem.getId());
                    twoSubject.setTitle(twoSubjectItem.getTitle());
                    oneSubject.getChildrens().add(twoSubject);
                }
            }
            items.add(oneSubject);
        }
        return items;
    }//查询所有一级分类跟二级分类
    public boolean addSubject(FileItem fileItem){
        try {
            //1 获取文件输入流
            InputStream inputStream = fileItem.getInputStream();
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, EduSubjectData.class, new SubjectExcelListener()).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
