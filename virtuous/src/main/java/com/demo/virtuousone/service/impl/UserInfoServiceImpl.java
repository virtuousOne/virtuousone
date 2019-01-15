package com.demo.virtuousone.service.impl;

import com.demo.virtuousone.dao.UserInfoMapper;
import com.demo.virtuousone.dto.UserInfoDTO;
import com.demo.virtuousone.po.UserInfoPO;
import com.demo.virtuousone.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/12/0012
 * Description: 实现用户service接口
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDTO selectByPrimaryKey(Long id) {
        UserInfoPO userInfoPO = userInfoMapper.selectByPrimaryKey(id);
        UserInfoDTO userInfoDTO = convertToDTO(userInfoPO);
        return userInfoDTO;
    }

    @Transactional
    public int deleteByPrimaryKey(Long id) throws RuntimeException {
        try {
            return userInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error("" + e.getMessage());
            throw new RuntimeException("删除异常");
        }
    }

    @Override
    public List<UserInfoDTO> getUserInfoList(UserInfoDTO vo) {
        List<UserInfoPO> userInfoPOS = userInfoMapper.getUserInfoList(vo);
        List<UserInfoDTO> userInfoDTOList = new ArrayList<>();
        for (UserInfoPO userInfoPO : userInfoPOS) {
            UserInfoDTO userInfoDTO = convertToDTO(userInfoPO);
            userInfoDTOList.add(userInfoDTO);
        }
        return userInfoDTOList;
    }

    @Override
    public int getUserInfoListCount(UserInfoDTO vo) {
        return userInfoMapper.getUserInfoListCount(vo);
    }


    /**
     * PO 转 DTO
     *
     * @param
     * @return
     */
    private UserInfoDTO convertToDTO(UserInfoPO userInfoPO) {

        if (null == userInfoPO) {
            userInfoPO = new UserInfoPO();

        }
        UserInfoDTO userInfoDTO = new UserInfoDTO(userInfoPO);
        return userInfoDTO;
    }

}
