package com.demo.virtuousone.common;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/20/0020
 * Description:
 */
public class ApiConstant {
    //aes 解析加密token
    public static final String AES_KEY_TOKEN = "*LQP2sPmE~c*cO`ZHY2]}qC6X)VNUg-U!1QvHLtv";
    //aes 解析加密token 商家登录专用
    public static final String AES_KEY_SHOP_TOKEN = "NGcBHIS8jJXYe&rW!q@2dnURyqdOU!1QvHLtv";
    //用户token redis key
    public static final String TOKEN_USERID = "token_%s";
    //用户注册手机号频率限制
    public static final String REG1_LIMIT = "reg1_limit_%s";
    //用户注册手机号频率限制
    public static final String REG2_LIMIT = "reg2_limit_%s";
    //限制用户当天同一个手机号注册次数
    public static final String REG_DAY_LIMIT = "reg_day_limit_%s";
    //用户注册ip限制
    public static final String USERREG_TEMPIP = "ureg_ip_limit_%s";
    //uuid当天请求次数限制
    public static final String UUID_LIMIT = "%s_limit";
    //签名字符串
    public static final String SIGN_STR = "149c9b3c6611459a8975d5db6ff24d10";


    //用户列表缓存
    public static final String USER_LIST = "user_list";
    //lock
    public static final String LOCK = "lock_%s";
}
