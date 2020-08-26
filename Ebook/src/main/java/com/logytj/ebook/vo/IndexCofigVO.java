package com.logytj.ebook.vo;

import java.io.Serializable;
import java.util.List;

public class IndexCofigVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer configType;
	private String configName;
	private String configSex;
	public List<BookVO> list;
	
	public void setConfigSex(String configSex) {
		this.configSex = configSex;
	}
	public String getConfigSex() {
		return configSex;
	}
	public void setConfigType(Integer configType) {
		this.configType = configType;
	}
	public Integer getConfigType() {
		return configType;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigName() {
		return configName;
	}
	
	public void setList(List<BookVO> list) {
		this.list = list;
	}
}
