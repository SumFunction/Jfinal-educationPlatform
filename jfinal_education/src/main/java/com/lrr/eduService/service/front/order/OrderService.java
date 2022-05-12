package com.lrr.eduService.service.front.order;

import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.EduTeacher;
import com.lrr.eduService.bean.TOrder;
import com.lrr.eduService.bean.UcenterMember;
import com.lrr.eduService.service.after.EduCourseService;
import com.lrr.common.orderUtils.OrderNoUtil;

import java.util.List;
import java.util.UUID;

/*
处理订单相关的类
 */
public class OrderService {
    private final TOrder dao = new TOrder().dao();
    private final EduCourseService eduCourseService = new EduCourseService();
    private final UcenterMember ucenterMember = new UcenterMember();
    private final EduTeacher teacherDao = new EduTeacher();
    public TOrder createOrder(String courseId,String userId){
        TOrder one = dao.findFirst("select * from t_order where courseId = ? and memberId = ?", courseId, userId);
        if (one != null)//表明已经有创建好的订单，直接返回
            return one;
        EduCourse course = eduCourseService.findCourseById(courseId);//查询课程信息
        UcenterMember user = ucenterMember.findById(userId);//查询用户信息
        TOrder tOrder = new TOrder();
        tOrder.setTeacherName(teacherDao.findById(course.getTeacherId()).getName());
        tOrder.setId(UUID.randomUUID().toString());
        tOrder.setOrderNo(OrderNoUtil.getOrderNo());
        tOrder.setStatus(0);//表明没购买
        tOrder.setCourseCover(course.getCover());
        tOrder.setCourseId(courseId);
        tOrder.setCourseTitle(course.getTitle());
        tOrder.setMemberId(userId);
        tOrder.setMobile(user.getMobile());
        tOrder.setNickname(user.getNickname());
        tOrder.setTotalFee(course.getPrice());
        tOrder.save();
        return tOrder;
    }
    public boolean isBuy(String courseId,String userId){
        List<TOrder> tOrders = dao.find("select * from t_order where courseId = ? and memberId = ?", courseId, userId);
        if(tOrders.size() > 0 && tOrders.get(0).getStatus() == 1)//如果能查出订单号，并且订单状态为已购买
            return true;
        return false;
    }
    public TOrder findOrderInfoByOrderNo(String orderNo){
        return dao.findFirst("select * from t_order where orderNo = ?",orderNo);
    }
}
