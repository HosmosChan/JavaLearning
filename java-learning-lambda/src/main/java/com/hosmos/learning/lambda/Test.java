package com.hosmos.learning.lambda;

import com.alibaba.fastjson.*;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            String str = obj.toString();
            return "".equals(str.trim());
        }
        if (obj instanceof List) {
            List<Object> list = (List<Object>) obj;
            return list.isEmpty();
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            return map.isEmpty();
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            return set.isEmpty();
        }
        if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length <= 0;
        }
        return false;
    }

    public static Map<String, Object> mappers(String jsonStr) {
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        Set<String> jsonKeySet = jsonObj.keySet();
        Map<String, Object> resultMap = new HashMap<>();
        for (String key : jsonKeySet) {
            resultMap.put(key, jsonObj.getString(key));
        }
        return resultMap;
    }

    public static String setJson(String... keys) {
        try {
            if (isEmpty(keys))
                return null;
            JSONObject jsonObj = new JSONObject();
            StringBuilder resultJson = new StringBuilder("{");
            boolean way = true;
            if (way) {
                //正常写法
                for (String str : keys) {
                    resultJson.append("\"").append(str).append("\":\"\",");
                }
            } else {
                //lambda写法
                List<String> key = Arrays.asList(keys);
                resultJson.append(key.stream().map(k -> {
                    return "\"" + k + "\":\"\",";
                }).collect(Collectors.joining()));
            }
            resultJson = new StringBuilder(resultJson.substring(0, resultJson.length() - 1));
            resultJson.append("}");
            return resultJson.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        /*String key = setJson("plate_num", "park_no", "pay_order", "order_status");
        System.err.println(key);
        String param = "{\"parkId\":000000,\"parkName\":\"测试停车场\",\"license\":\"京A12345\",\"parkInTime\":\"2018-10-03 15:25:07\",\"parkOutTime\":\"2018-11-02 16:25:07\",\"unPayed\":0.1,\"amount\":0.1,\"billID\":99999}";
        Map<String, Object> map = mappers(param);
        System.err.println(map.get("parkName"));*/
        System.err.println("1");

    }
}
