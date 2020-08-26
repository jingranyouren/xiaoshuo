package com.logytj.ebook.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;



/**
*  user
* @author logytj 2020-08-13
*/
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * user_id
    */
    private Integer userId;

    /**
    * nick_name
    */
    private String nickName;

    /**
    * 头像地址
    */
    private String avatarUrl;

    /**
    * 性别 check (sex in( 男 ， 女 ))
    */
    private String sex;

    /**
    * 注册时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Override
    public String toString() {
    	return "[id:"+userId+",nickName:"+nickName+",avatarUrl:"+avatarUrl+"sex:"+sex+",createTime:"+createTime;
    }

}

