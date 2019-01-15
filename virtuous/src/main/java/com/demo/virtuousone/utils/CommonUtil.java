package com.demo.virtuousone.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/13/0013
 * Description: 公共工具类
 */
public class CommonUtil {
    public static final Random random = new Random(System.currentTimeMillis());
    public static final char[] NUM = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);


    /**
     * 获取时间戳
     */
    public static Long getTimeStamp() {
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成多少位的随机数
     */
    public static String generateNum(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = count; i > 0; i--) {
            sb.append(NUM[random.nextInt(NUM.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成多少位的随机数
     */
    public static String randomString(int count) {
        // 生成数字大小写字母
//        return  RandomStringUtils.randomAlphanumeric(count);
        // 生成数字
        return RandomStringUtils.randomNumeric(count);

    }

    /**
     * 生成多少位的随机数数字大小写字母
     */
    public static String randomAlphanumeric(int count) {
        // 生成数字大小写字母
        return RandomStringUtils.randomAlphanumeric(count);
    }

    /**
     * 邮箱验证
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String clearXss(String value) {

        if (value == null || "".equals(value)) {
            return value;
        }
        value = value.replaceAll("<", "<").replaceAll(">", ">");
        value = value.replaceAll("\\(", "(").replace("\\)", ")");
        value = value.replaceAll("'", "'");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replace("script", "");
        return value;
    }

    public static String getYYYYMMddHHmmss() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
//    /**
//     * 获取 yyyy-mm-dd hh:mm:ss
//     * @return
//     */
//    public static String getYYYY(){
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }

    public static String getYYYYMMddHHmmssRandomStr() {

        return getYYYYMMddHHmmss() + randomString(5);
    }


    /**
     * 生成推荐码
     *
     * @return
     */
    public static String createInviteCode() {

        return randomString(5);

    }

    /**
     * 获取年月日时分秒
     *
     * @return
     */
    public static String getTime() {

        return LocalDate.now() + " " + LocalTime.now().withNano(0);

    }

    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            //   MToast.showToast("手机号应为11位数");
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            //boolean isMatch = m.matches();
            // LogUtil.e(isMatch);
            if (!m.matches()) {
                return false;
                //       MToast.showToast("请填入正确的手机号");
            }
            return m.matches();
        }
    }


    /**
     * 返回距离今天结束还有多少秒
     *
     * @return
     */
    public static Long getSecondDistanceTodayEnd() {

        LocalDateTime midnight = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        //  long millSeconds = ChronoUnit.MILLIS.between(LocalDateTime.now(),midnight);
        return ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
//        long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), midnight);
        //    System.out.println("当天剩余毫秒：" + millSeconds);
        //  System.out.println("当天剩余秒：" + seconds);

    }

}
