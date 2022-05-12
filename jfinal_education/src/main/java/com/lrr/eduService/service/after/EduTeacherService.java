package com.lrr.eduService.service.after;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lrr.eduService.bean.EduTeacher;
import com.lrr.eduService.bean.vo.TeacherQueryVo;
import net.sf.json.JSONObject;

import java.util.*;

public class EduTeacherService {
    private final EduTeacher dao = new EduTeacher().dao();
    public List<EduTeacher> findAll(){
        return dao.findAll();
    }
    public EduTeacher findById(String id){
        return dao.findById(id);
    }
    public boolean deleteTeacherById(String id){
        return dao.deleteById(id);
    }
    public Map<String,Object> findTeacherByPages(String current,String limit,String json){
        //Db查询
        Page<Record> data = null;
        if ("{}".equals(json) || "".equals(json)){

            data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_teacher");
        }else{
            TeacherQueryVo condition  = (TeacherQueryVo)new JSONObject().toBean(JSONObject.fromObject(json), TeacherQueryVo.class);
            String name = condition.getName();
            String level = condition.getLevel();
            if (name == null && level != null)
                data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_teacher where level=?",level);
           if(level == null && name != null)
               data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_teacher where name=?",name);
           if(name!=null && level!= null)
               data = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from edu_teacher where level=? and name=?",level,name);
        }
        //封装分页数据
        ArrayList<EduTeacher> eduTeachers = new ArrayList<>();
        List<Record> list = data.getList();
        for(Record record : list){
            EduTeacher eduTeacher = new EduTeacher();
            eduTeacher = eduTeacher.put(record);
            /*eduTeacher.setName(record.get("name"));
            eduTeacher.setIntro(record.get("intro"));
            eduTeacher.setAvatar(record.get("avatar"));
            eduTeacher.setLevel(record.get("level"));
            eduTeacher.setId(record.get("id"));*/
            eduTeachers.add(eduTeacher);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("items",eduTeachers);
        map.put("total",data.getTotalRow());
        return map;
    }
    public boolean addTeacher(String json){
        String id = UUID.randomUUID().toString();
        EduTeacher eduTeacher  = (EduTeacher) new JSONObject().toBean(JSONObject.fromObject(json), EduTeacher.class);
        eduTeacher.setId(id);
        boolean save = eduTeacher.save();
        return save;
    }
    public boolean updateTeacher(String json){
        EduTeacher eduTeacher  = (EduTeacher) new JSONObject().toBean(JSONObject.fromObject(json), EduTeacher.class);
        boolean update = eduTeacher.update();
        return update;
    }
}
