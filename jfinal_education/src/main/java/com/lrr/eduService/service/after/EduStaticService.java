package com.lrr.eduService.service.after;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.lrr.common.status.Result;
import com.lrr.eduService.bean.TOrder;

import java.math.BigDecimal;
import java.util.*;

public class EduStaticService {
    private TOrder tOrder = new TOrder();
    public Result getCourseSell(){
        List<Record> records = Db.find("select courseTitle from t_order group by courseTitle");
        Map<String,Double> map = new HashMap<>();
        if(records.size() != 0){
            for(Record record : records){
                String courseTitle = (String)record.get("courseTitle");
                double money = 0;
                List<TOrder> tOrders = tOrder.find("select * from t_order where courseTitle = ?", courseTitle);
                for(TOrder tOrder : tOrders){
                    BigDecimal totalFee1 = tOrder.getTotalFee();
                    money += totalFee1.doubleValue();
                }
                map.put(courseTitle,money);
            }
        }
        Map<String, Object> stringDoubleMap = sortMap(map);
        return Result.ok().data(stringDoubleMap);
    }
    public static Map<String, Object> sortMap(Map<String, Double> map) {
        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<String, Double>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<String, Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                //正序排列，倒序反过来
                if(o1.getValue() - o2.getValue() > 0)
                    return 1;
                else if(o1.getValue() - o2.getValue() == 0)
                    return 0;
                else return -1;
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<String, Object>();
        for (Map.Entry<String,Double> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }
}
