package com.demo.virtuousone.dto;

import com.demo.virtuousone.po.UserInfoPO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/12/0012
 * Description: 用户实体，与页面交互DTO
 */
public class UserInfoDTO implements Serializable {
    private static final long serialVersionUID = -76361824787555032L;
    private Long id;
    private String username;
    private String email;
    private String qq;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private Integer start = 0;  //从第多少条开始
    private Integer length = 10;  //取多少条
    private Integer draw = 1;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public UserInfoDTO(UserInfoPO userInfoPO) {
        this.setId(userInfoPO.getId());
        this.setUsername(userInfoPO.getUsername());
        this.setEmail(userInfoPO.getEmail());
        this.setQq(userInfoPO.getQq());
        this.setCreateTime(userInfoPO.getCreateTime());
        this.setUpdateTime(userInfoPO.getUpdateTime());
    }

    public UserInfoDTO() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
