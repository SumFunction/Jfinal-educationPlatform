package com.lrr.eduService.service.front;

import com.lrr.eduService.bean.UcenterMember;
import com.lrr.common.token.JwtUtils;
import net.sf.json.JSONObject;

import java.util.List;

public class LoginService {
    private final UcenterMember dao = new UcenterMember().dao();
    public String login(String json){
        UcenterMember ucenterMember = (UcenterMember)new JSONObject().toBean(JSONObject.fromObject(json), UcenterMember.class);
        String nickname = ucenterMember.getNickname();
        String password = ucenterMember.getPassword();
        List<UcenterMember> ucenterMembers = ucenterMember.find("select * from ucenter_member where nickname = ? and password = ?", nickname, password);
        if(ucenterMembers.size() > 0){
            //生成token
            ucenterMember = ucenterMembers.get(0);
            //将id，昵称放入token信息中
            String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
            return token;
        }//代表登陆成功
        return null;
    }
    public UcenterMember getUserInfo(String id){
        UcenterMember byId = dao.findById(id);
        if(byId != null)
            return byId;
        return null;
    }
}
