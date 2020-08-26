package com.logytj.ebook.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;



public class DiscussVO implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	    private User user;


	    /**
	    * discuss_time
	    */
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

	    public User getUser() {
	        return user;
	    }

	    public void setUserId(User user) {
	        this.user = user;
	    }

//	    public Integer getBookId() {
//	        return bookId;
//	    }
//
//	    public void setBookId(Integer bookId) {
//	        this.bookId = bookId;
//	    }

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
