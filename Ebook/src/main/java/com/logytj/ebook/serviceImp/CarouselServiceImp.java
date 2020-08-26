package com.logytj.ebook.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logytj.ebook.dao.CarouselDao;
import com.logytj.ebook.entity.CarouselEntity;
import com.logytj.ebook.service.CarouselService;

@Service
public class CarouselServiceImp implements CarouselService {
	
	@Autowired
	private CarouselDao carouselDao;
	
	@Override
	public List<CarouselEntity> getCarouselBySex(String sex, int limit) {
		// TODO Auto-generated method stub
		return carouselDao.selectCarouselBySex(sex, limit);
	}

}
