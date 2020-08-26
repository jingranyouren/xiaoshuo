package com.logytj.ebook.vo;

import java.io.Serializable;
import java.util.List;

import com.logytj.ebook.entity.StyleEntity;

public class MoreBookVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer bookId;
	private String bookName;
	private String bookImg;
	private List<StyleEntity> tabs;
	private String intro;
	
	
	public List<StyleEntity> getTabs(){
		return tabs;
	}
	public void setTabs(List<StyleEntity> tabs) {
		this.tabs = tabs;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
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
