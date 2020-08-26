package com.logytj.ebook.entity;

import java.io.Serializable;
import java.util.Date;

/**
*  read_notes
* @author logytj 2020-08-13
*/
public class ReadNotesEntity implements Serializable {

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
    * 上次阅读时间
    */
    private Date lastReadTime;

    /**
     * 上次阅读到的章节
     */
    private Integer lastReadContentId;
    
    public Integer getLastReadContentId() {
    	return lastReadContentId;
    }
    public void setLastReadContentId(Integer lastReadContentId) {
    	this.lastReadContentId = lastReadContentId;
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

    public Date getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(Date lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

}
