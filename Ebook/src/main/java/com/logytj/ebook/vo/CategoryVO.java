package com.logytj.ebook.vo;

public class CategoryVO {
	
	private Integer id;
	private String name;
	private boolean is_active;
	
	public CategoryVO() {
		
	}
	
	public CategoryVO(Integer id,String name,boolean is_active) {
		this.id = id;
		this.name = name;
		this.is_active = is_active;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	public boolean getIs_active() {
		return is_active;
	}
}
