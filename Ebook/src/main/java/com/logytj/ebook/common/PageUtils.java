package com.logytj.ebook.common;

import java.util.HashMap;
import java.util.Map;

public class PageUtils extends HashMap<String,Object>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int page;	//当前页
	private int limit;  //一页的数据
	
	public PageUtils(Map<String,Object> param) {
		this.putAll(param);
		page = Integer.parseInt(param.get("page").toString());
		limit = Integer.parseInt(param.get("limit").toString());
		Integer start = (page - 1) * limit;
		this.put("start",start);
		this.put("page",page);
		this.put("limit",limit);
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getLimint() {
		return limit;
	}

}
