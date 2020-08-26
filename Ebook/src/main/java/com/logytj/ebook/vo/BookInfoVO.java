package com.logytj.ebook.vo;

import java.io.Serializable;
import java.util.List;

import com.logytj.ebook.entity.StyleEntity;

public class BookInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bookImg;
	private String bookName;
	private String author;
	private String status;
	private Float wordNum;
	private List<StyleEntity> tabs;
	private String intro;
	private List<DiscussVO> disList;
	private Boolean isInShell;
	private String firstCName;
	private String lastCName;
	private Integer count;
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getFirstCName() {
		return firstCName;
	}
	public void setFirstCName(String firstCName) {
		this.firstCName = firstCName;
	}
	public String getLastCName() {
		return lastCName;
	}
	public void setLastCName(String lastCName) {
		this.lastCName = lastCName;
	}
	public Float getWordNum() {
		return wordNum;
	}
	public void setWordNum(Float wordNum) {
		this.wordNum = wordNum;
	}
	public String getBookImg() {
		return bookImg;
	}
	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String  getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public List<DiscussVO> getDisList(){
		return disList;
	}
	public void setDisList(List<DiscussVO> disList) {
		this.disList = disList;
	}
	
	public Boolean getIsInShell() {
		return isInShell;
	}
	
	public void setIsInShell(Boolean isInShell) {
		this.isInShell = isInShell;
	}

}
