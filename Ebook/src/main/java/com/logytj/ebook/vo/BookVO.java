package com.logytj.ebook.vo;

import java.io.Serializable;

public class BookVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer bookId;
	private String bookName;
	private String bookImg;
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	
	public Integer getBookId() {
		return bookId;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	public String getbookImg() {
		return bookImg;
	}

}
