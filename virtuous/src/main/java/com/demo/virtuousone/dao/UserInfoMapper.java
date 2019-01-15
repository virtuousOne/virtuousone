package com.demo.virtuousone.dao;

import com.demo.virtuousone.dto.UserInfoDTO;
import com.demo.virtuousone.po.UserInfoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/12/0012
 * Description: dao接口
 */
//@Mapper 如果使用这个注解，就不要在主启动类写@MapperScan(basePackages="com.demo.virtuousone.dao")注解
public interface UserInfoMapper {

    int deleteByPrimaryKey(Long id);

    UserInfoPO selectByPrimaryKey(Long id);


    List<UserInfoPO> getUserInfoList(@Param("v") UserInfoDTO v);

    int getUserInfoListCount(@Param("v") UserInfoDTO vo);

}
