package com.demo.virtuousone.utils;

import com.alibaba.fastjson.JSONObject;
import com.mzlion.easyokhttp.HttpClient;
import org.apache.commons.lang.StringUtils;

/**
 * @Auther: 吴宸煊
 * @Date: 2019/1/14
 * @Description: 短信工具类
 */
public class SMSUtils {
    // 引用了 easy-httpClient
    private static final String SMS_RESPONSE_CODE = "0";


    /**
     * 253 发送国内短信验证码
     *
     * @param phone
     * @param smsCode
     * @return
     */
    public static Boolean sendSmsCodeBy253(String phone, String smsCode) {

        String sms = "{\"account\":\"N3040586\",\"password\":\"gAnTHY5ROM32o9\",\"msg\":\"您的验证码 %s\",\"phone\":\"%s\"}";

        String responseData = HttpClient
                .textBody("https://smssh1.253.com/msg/send/json")
                .json(String.format(sms, smsCode, phone))
                .charset("utf-8")
                .asString();
//        System.out.println(responseData);
        String code = (String) JSONObject.parseObject(responseData).get("code");
        if (StringUtils.equals(code, SMS_RESPONSE_CODE)) {
            return true;
        }
        return false;

    }

}
