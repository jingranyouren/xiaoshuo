package com.logytj.ebook.common;

public class ResultGenerator {
	public static final int SUCCESS_CODE = 200;
	public static final int ERROR_CODE = 500;
	public static final String DEFAULT_SUCCESS_MSG = "success";
	public static final String DEFAULT_ERROR_MSG = "error";
	
	public static Result genSuccess() {
		return new Result(SUCCESS_CODE,DEFAULT_SUCCESS_MSG);
	}
	
	public static Result genFailed(){
		return new Result(ERROR_CODE,DEFAULT_ERROR_MSG);
	}
	
	public static  Result genSuccess(Object data){
		Result result = new Result(SUCCESS_CODE,DEFAULT_SUCCESS_MSG);
		result.setData(data);
		return result;
	}
	
	public static Result genFailed(String msg){
		Result result = new Result();
		result.setCode(ERROR_CODE);
		if(msg != null && !"".equals(msg.trim()))
			result.setMsg(msg);
		else
			result.setMsg(DEFAULT_ERROR_MSG);
		return result;
	}
	
	public static Result genFailed(int code,String msg) {
		Result result = new Result();
		result.setCode(code);
		if(msg != null && !"".equals(msg.trim()))
			result.setMsg(msg);
		else
			result.setMsg(DEFAULT_ERROR_MSG);
		return result;
	}
	
}
