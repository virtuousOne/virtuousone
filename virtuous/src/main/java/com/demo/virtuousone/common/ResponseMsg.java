package com.demo.virtuousone.common;

import com.demo.virtuousone.utils.CommonUtil;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 返回对象
 */
public class ResponseMsg {
    private static final String MSG = "操作成功";
    private static final Integer SUCCESS = 200;
    private static final Integer ERROR = 0;

    public static Map<String, Object> success() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", SUCCESS);
        return map;
    }

    public static Map<String, Object> success(Object data) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", SUCCESS);
        if (null != data) {
            map.put("data", data);
        }
        map.put("msg", MSG);
        map.put("serverTime", CommonUtil.getTimeStamp());
        return map;
    }

    public static Map<String, Object> success(Map<String, Object> data, String msg) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", SUCCESS);
        if (null != data) {
            map.put("data", data);
        }
        map.put("msg", msg);
        map.put("serverTime", CommonUtil.getTimeStamp());
        return map;
    }

    public static Map<String, Object> error(Map<String, Object> data, String msg) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", ERROR);
        if (null != data && !data.isEmpty()) {
            map.put("data", data);
        }
        map.put("msg", msg);
        map.put("serverTime", CommonUtil.getTimeStamp());
        return map;
    }

    public static Map<String, Object> error(Object msg) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", ERROR);
        map.put("msg", msg);
        map.put("serverTime", CommonUtil.getTimeStamp());
        return map;
    }


    public static Map<String, Object> errorCode(Integer errCode) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", errCode);
        // map.put("msg", msg);
        map.put("serverTime", CommonUtil.getTimeStamp());
        return map;
    }

}
