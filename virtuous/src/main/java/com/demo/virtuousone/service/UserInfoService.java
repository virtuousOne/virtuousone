package com.demo.virtuousone.service;

import com.demo.virtuousone.dto.UserInfoDTO;

import java.util.List;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/12/0012
 * Description: 用户service接口
 */
public interface UserInfoService {

    UserInfoDTO selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id) throws RuntimeException;

    List<UserInfoDTO> getUserInfoList(UserInfoDTO vo);

    int getUserInfoListCount(UserInfoDTO vo);
}
