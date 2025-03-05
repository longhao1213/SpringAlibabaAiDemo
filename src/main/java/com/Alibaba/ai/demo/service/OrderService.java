package com.Alibaba.ai.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static HashMap<String, List<String>> orderMap;

    static {
        orderMap = new HashMap<>();
        // 为每个用户添加订单
        orderMap.put("龙三", new ArrayList<String>() {{
            add("苹果 iPhone 14 Pro Max");
            add("小米 Mi 智能手环 7");
            add("索尼 WH-1000XM5 降噪耳机");
        }});

        orderMap.put("李四", new ArrayList<String>() {{
            add("三星 Galaxy S22");
            add("Bose QuietComfort 45 蓝牙耳机");
        }});

        orderMap.put("王五", new ArrayList<String>() {{
            add("Dyson 戴森 V11 无绳吸尘器");
            add("耐克 Air Max 2022 跑步鞋");
            add("亚马逊 Echo Show 10 智能音响");
            add("苹果 MacBook Pro 16 英寸笔记本");
        }});
    }

    public String getOrder(String userName) {
        List<String> strings = orderMap.get(userName);
        return String.join(",", strings);
    }

    public String deleteOrder(String userName, String orderName) {
        List<String> strings = orderMap.get(userName);
        return strings.remove(orderName) ? "删除成功" : "删除失败";
    }
}
