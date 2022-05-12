package com.lrr.eduService.controller.front.order;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

import com.lrr.common.status.Result;
import com.lrr.eduService.bean.TOrder;
import com.lrr.eduService.service.front.order.OrderService;
import com.lrr.common.token.JwtUtils;
@Path("/eduservice/front/order")
public class OrderController extends Controller {
    private final OrderService orderService = new OrderService();
    //根据用户token以及课程id创建订单
    public void createOrder(){
        String courseId = get("courseId");
        String token = getRequest().getHeader("token");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        TOrder order = orderService.createOrder(courseId, userId);
        renderJson(Result.ok().data("item",order));
    }
    //根据用户token以及课程id判断是否已经购买了该课程
    public void isBuy(){
        String courseId = get("courseId");
        String token = getRequest().getHeader("token");
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        boolean buy = orderService.isBuy(courseId, userId);
        renderJson(Result.ok().data("isBuy",buy));
    }
    //根据订单号，查询订单信息
    public void findOrderInfoByOrderNo(){
        renderJson(Result.ok().data("item",orderService.findOrderInfoByOrderNo(get("orderNo"))));
    }
}
