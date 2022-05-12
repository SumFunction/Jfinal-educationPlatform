package com.lrr.eduService.controller.front.order;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lrr.common.status.Result;
import com.lrr.eduService.service.front.order.PayLogService;

/*
处理支付相关的处理类
 */
@Path("/eduservice/front/pay")
public class PayLogController extends Controller {
    private final PayLogService payLogService = new PayLogService();
    public void createPay() {
        String orderNo = get("orderNo");
        String payForm = payLogService.createPay(orderNo);
        if (payForm != null) {
            renderJson(Result.ok().data("payForm", payForm));
        } else {
            renderJson(Result.error().message("抱歉，支付页面调用失败！请稍后试试！"));
        }
    }
    //用户支付完后，支付宝会发送一个异步请求到这controller用于通知服务器支付完毕
    public void getPayResult(){
        String out_trade_no = get("out_trade_no");//获取订单编号，查询一下用户是否已经支付完毕
        boolean b = payLogService.queryOrderStatus(out_trade_no);
        if(b){//如果用户已经支付成功，则修改对应订单状态
            payLogService.changeOrderStatus(out_trade_no);
        }
        renderText("您已经成功付款！");//TODO 后期加个好看的模板上去
    }
}
