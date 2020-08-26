package com.logytj.ebook.vo;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickName;

    /**
    * 头像地址
    */
    private String avatarUrl;
     

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
}
