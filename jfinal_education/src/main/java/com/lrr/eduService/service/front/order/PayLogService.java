package com.lrr.eduService.service.front.order;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lrr.eduService.bean.EduCourse;
import com.lrr.eduService.bean.TOrder;
import com.lrr.common.orderUtils.AlipayConfig;

import java.math.BigInteger;

public class PayLogService {
    //创建交易界面
    private final TOrder dao = new TOrder().dao();
    private final DefaultAlipayClient defaultAlipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APP_ID, AlipayConfig.PRIVATE_KEY, "json", "UTf-8", AlipayConfig.PUBLIC_KEY, "RSA2");
    public String createPay(String orderNo){
        TOrder order = dao.findFirst("select * from t_order where orderNo = ?", orderNo);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        request.setReturnUrl(AlipayConfig.RETURN_URL);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", order.getOrderNo());
        bizContent.put("total_amount", order.getTotalFee());
        bizContent.put("subject", order.getCourseTitle());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = null;
        String form = null;
        try {
            response = defaultAlipayClient.pageExecute(request);
            form = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return form;//调用成功，会返回一个支付表单string
    }
    //查询订单是否已经被成功支付
    public boolean queryOrderStatus(String out_trade_no){
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", out_trade_no);
        request.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse response = null;
        String body = null;
        try {
            response = defaultAlipayClient.execute(request);
            body = response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            System.out.println(body);
            JSONObject jsonObject = JSONObject.parseObject(body);
            JSONObject alipay_trade_query_response = (JSONObject)jsonObject.get("alipay_trade_query_response");
            String trade_status = (String)alipay_trade_query_response.get("trade_status");
            if("TRADE_SUCCESS".equals(trade_status)){
                //代表订单已经支付成功
                TOrder first = dao.findFirst("select * from t_order where orderNo = ?", out_trade_no);
                if(first != null){
                    String courseId = (String)first.get("courseId");
                    EduCourse eduCourse = new EduCourse();
                    EduCourse course = eduCourse.findById(courseId);
                    BigInteger add = course.getBuyCount().add(BigInteger.valueOf(1));
                    course.setBuyCount(add);
                    course.update();//更新下销售量
                }
                return true;
            }

        }
        return false;
    }
    public void changeOrderStatus(String out_trade_no){//修改订单状态
        TOrder first = dao.findFirst("select * from t_order where orderNo = ?", out_trade_no);
        first.setStatus(1);
        first.update();
    }
}
