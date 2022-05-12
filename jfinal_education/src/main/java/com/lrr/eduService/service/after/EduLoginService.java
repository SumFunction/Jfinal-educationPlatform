package com.lrr.eduService.service.after;

import com.lrr.eduService.bean.PlcMember;
import com.lrr.common.token.JwtUtils;
import net.sf.json.JSONObject;

import java.util.List;

public class EduLoginService {
    private final PlcMember dao = new PlcMember();
    public String login(String json){
        PlcMember plcMember = (PlcMember)new JSONObject().toBean(JSONObject.fromObject(json), PlcMember.class);
        String username = plcMember.getUsername();
        String password = plcMember.getPassword();
        List<PlcMember> plcMembers = dao.find("select * from plc_member where username = ? and password = ?", username, password);
        if(plcMembers.size() > 0){
            //生成token
            plcMember = plcMembers.get(0);
            //将id，昵称放入token信息中
            String token = JwtUtils.getJwtToken(plcMember.getId(), plcMember.getUsername());
            return token;
        }//代表登陆成功
        return null;
    }
    public PlcMember info(String id){
        PlcMember byId = dao.findById(id);
        if(byId != null)
            return byId;
        return null;
    }
}
