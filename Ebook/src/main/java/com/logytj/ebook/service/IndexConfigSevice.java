package com.logytj.ebook.service;

import java.util.List;


import com.logytj.ebook.vo.IndexCofigVO;
import com.logytj.ebook.vo.MoreBookVO;

public interface IndexConfigSevice {
	List<IndexCofigVO> getItemForIndex(String sex);
	
	List<MoreBookVO> getMorebyConfigTypeAndSex(Integer configType,String sex);
}
