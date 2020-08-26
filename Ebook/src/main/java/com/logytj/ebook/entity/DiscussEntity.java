package com.logytj.ebook.entity;

import java.io.Serializable;
import java.util.Date;

/**
*  discuss
* @author logytj 2020-08-13
*/
public class DiscussEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * user_id
    */
    private Integer userId;

    /**
    * book_id
    */
    private Integer bookId;

    /**
    * discuss_time
    */
    private Date discussTime;

    /**
    * 评论内容
    */
    private String content;

    private Integer agreen;
    
    public Integer getAgreen(){
    	return agreen;
    }
   
    public void setAgreen(Integer agreen) {
    	this.agreen = agreen;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Date getDiscussTime() {
        return discussTime;
    }

    public void setDiscussTime(Date discussTime) {
        this.discussTime = discussTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}

