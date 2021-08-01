package com.demo.virtuousone.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.virtuousone.po.UserInfoPO;
import com.demo.virtuousone.utils.TransferUtils;
import com.mzlion.core.utils.BeanUtils;

import java.util.Date;

/**
 * @description:
 * @author: Chenxuan.wu
 * @create: at 2021-08-01 12:12
 */
public class demoServiceImpl {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        UserInfoPO u1 = new UserInfoPO();
        u1.setQq("123");
        u1.setCreateTime(new Date());
        u1.setUsername("张三");
        UserInfoPO u2 = new UserInfoPO();
        TransferUtils.transferBean(u1,u2);
        long endTime = System.currentTimeMillis();
        System.out.println("当前程序耗时：" + (endTime - startTime) + "ms"+"输出u2:}{}"+"\n"+ JSON.toJSONString(u2));
    }
}
