package com.lrr.eduService.service.after;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.PlcMember;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EduPlcService {
    private final PlcMember dao = new PlcMember();
    public List<PlcMember> getAllMember(){
        return dao.findAll();
    }
    public boolean deleteMemberById(String id){
        return dao.deleteById(id);
    }
    public boolean updateMember(String json){
        PlcMember plcMember = (PlcMember)new JSONObject().toBean(JSONObject.fromObject(json), PlcMember.class);
        return plcMember.update();
    }
    public boolean addMember(String json){
        PlcMember plcMember = (PlcMember)new JSONObject().toBean(JSONObject.fromObject(json), PlcMember.class);
        plcMember.setId(UUID.randomUUID().toString());
        return plcMember.save();
    }
    public PlcMember getMemberInfoById(String id){
        return dao.findById(id);
    }
    public List<PlcMember> findByPages(String current,String limit,String aut){
        Page<Record> records = null;
        if ("".equals(aut) || aut == null){
            records = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from plc_member");
        }else{
            records = Db.paginate(Integer.valueOf(current), Integer.valueOf(limit), "select *", "from plc_member where auto = ?", aut);
        }
        List<Record> list = records.getList();
        List<PlcMember> items = new ArrayList<>();
        for(Record li : list){
            PlcMember plcMember = new PlcMember();
            PlcMember put = plcMember.put(li);
            items.add(put);
        }
        return items;
    }
}
