package com.logytj.ebook.common;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int currentPage; //当前页
	
	private int allPage; //总页数
	
	private int rotesNum; // 页记录数
	
	private int allRotesNum; //总记录数
	
	private List<?> list;
	public PageResult(List<?> list,int currentPage,int rotesNum,int allRotesNum) {
		this.list = list;
		this.currentPage = currentPage;
		this.allRotesNum = allRotesNum;
		this.rotesNum = rotesNum;
		this.allPage = (int) Math.ceil((double) allRotesNum / rotesNum);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	
	public int getAllPage() {
		return allPage;
	}
	
	public void setAllRotesNum(int allRotesNum) {
		this.allRotesNum = allRotesNum;
	}
	
	public void setRotesNum(int rotesNum) {
		this.rotesNum = rotesNum;
	}
	
	public int getAllRotesNum() {
		return allRotesNum;
	}
	
	public int getRotesNum() {
		return rotesNum;
	}
	
	public List<?> getList(){
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
}
