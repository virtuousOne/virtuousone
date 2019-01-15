package com.demo.virtuousone.controller;

import com.demo.virtuousone.dto.UserInfoDTO;
import com.demo.virtuousone.service.UserInfoService;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/12/0012
 * Description: 基本的增删改查
 */
@RestController
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private UserInfoService userInfoService;
    private Boolean env = true;

    @RequestMapping("/")
    public String index() {
        logger.error("报错了");
        return env == true ? "virtuousOne 当前是生成环境" : "virtuousOne 当前不是生成环境";
    }

    @RequestMapping("getUserInfoList")
    @CrossOrigin(value = {"*"})
    public Map getUserInfoList(UserInfoDTO vo) {
        Map map = Maps.newHashMap();
        List<UserInfoDTO> list = userInfoService.getUserInfoList(vo);
        int count = userInfoService.getUserInfoListCount(vo);
        map.put("draw", Integer.valueOf(vo.getDraw()));//不要直接从参数直接返回，防止xss攻击。要转换成整型
        map.put("recordsTotal", count);
        map.put("recordsFiltered", count);
        map.put("data", list);
        return map;
    }

    @RequestMapping("getUserInfo2")
    public UserInfoDTO getUserInfo2() {
        return userInfoService.selectByPrimaryKey(Long.valueOf(1));
    }

    @RequestMapping("deleteUserInfoById")
    public int deleteUserInfoById() {
        return userInfoService.deleteByPrimaryKey(Long.valueOf(1));
    }


}
