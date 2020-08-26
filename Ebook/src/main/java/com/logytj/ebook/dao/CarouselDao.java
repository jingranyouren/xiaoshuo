package com.logytj.ebook.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.logytj.ebook.entity.CarouselEntity;

@Mapper
public interface CarouselDao {
	List<CarouselEntity> selectCarouselBySex(@Param("sex")String sex,@Param("limit") int limit);
}
