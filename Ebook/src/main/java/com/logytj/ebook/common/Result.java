package com.logytj.ebook.common;

public class Result {
	public int code;
	public String msg;
	public Object data;
	
	public Result() {
		
	}
	public Result(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public Object getData() {
		return data;
	}
}
