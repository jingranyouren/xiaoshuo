package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.logytj.ebook.entity.IndexConfigEntity;

@Mapper
public interface IndexConfigDao {
	public List<IndexConfigEntity> selectIndexConfigByTypeAndSex(
			@Param("configType") int configType,
			@Param("sex")String sex,
			@Param("limit")int limit);
}
