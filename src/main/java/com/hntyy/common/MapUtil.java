package com.hntyy.common;

import java.lang.reflect.Field;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Map工具类
 */
@Slf4j
public class MapUtil {

    /**
     *  根据map的key进行字典升序排序
     * @param map
     * @return map
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object>map) {
        Map<String, Object> treemap = new TreeMap<String, Object>(map);
        List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(treemap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return treemap;
    }

    /**
     * 将map转换成url参数
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue().toString());
                sb.append("&");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    /**
     * 将对象装换为map
     *
     * @param obj
     * @return
     */
    public static Map objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }

        Map map = new HashMap();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    // 测试对象转map，map按key升序，map转换成url参数
    public static void main(String[] args) {

    }

}
