package com.demo.virtuousone.po;

import com.demo.virtuousone.dto.UserInfoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 吴宸煊
 * Date: Created in  2019/1/12/0012
 * Description: 用户实体，与数据库交互
 */
public class UserInfoPO implements Serializable {
    private static final long serialVersionUID = -763618247875550322L;
    private Long id;
    private String username;
    private String email;
    private String qq;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    public UserInfoPO(UserInfoDTO dto) {
        this.setId(dto.getId());
        this.setUsername(dto.getUsername());
        this.setEmail(dto.getEmail());
        this.setQq(dto.getQq());
        this.setCreateTime(dto.getCreateTime());
        this.setUpdateTime(dto.getUpdateTime());
    }

    public UserInfoPO() {

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
