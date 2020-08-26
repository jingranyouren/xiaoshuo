package com.logytj.ebook.service;

import java.util.List;

import com.logytj.ebook.entity.CarouselEntity;

public interface CarouselService {
	List<CarouselEntity> getCarouselBySex(String sex,int limit);
}
