package com.logytj.ebook.entity;

import java.io.Serializable;
import java.util.Date;


/**
*  add_bookshell
* @author logytj 2020-08-13
*/
public class AddBookshellEntity implements Serializable {

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
    * 加入书架的时间
    */
    private Date addTime;


    

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    @Override
    public boolean equals(Object anotherClass) {
    	if(anotherClass == null)
    		return false;
    	if(this == anotherClass)
    		return true;
    	if(this.getClass() != anotherClass.getClass())
    		return false;
    	AddBookshellEntity other = (AddBookshellEntity)anotherClass;
    	if(this.bookId == other.getBookId() && this.userId == other.getUserId()
    			&& this.addTime.getTime() == other.getAddTime().getTime())
    		return true;
    	return false;
    }
}
