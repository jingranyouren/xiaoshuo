package com.logytj.ebook.vo;

import java.io.Serializable;

public class BookContentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 /**
	    * ���
	    */
	    private String bookName;

	    /**
	    * �½�id
	    */
	    private Integer bookContentId;

	    /**
	    * �½�����·��
	    */
	    private String bookContent;

	    private String bookContentName;
	    public String getBookContentName() {
	    	return bookContentName;
	    }
	    public void setBookContentName(String bookContentName) {
	    	this.bookContentName = bookContentName;
	    }

	    public String getBookName() {
	        return bookName;
	    }

	    public void setBookName(String bookName) {
	        this.bookName = bookName;
	    }

	    public Integer getBookContentId() {
	        return bookContentId;
	    }

	    public void setBookContentId(Integer bookContentId) {
	        this.bookContentId = bookContentId;
	    }

	    public String getBookContent() {
	        return bookContent;
	    }

	    public void setBookContent(String bookContent) {
	        this.bookContent = bookContent;
	    }

}
