package com.logytj.ebook.serviceImp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logytj.ebook.common.Constants;
import com.logytj.ebook.dao.BookDao;
import com.logytj.ebook.dao.CategoryDao;
import com.logytj.ebook.dao.RootCategoryDao;
import com.logytj.ebook.dao.StyleDao;
import com.logytj.ebook.entity.BookEntity;
import com.logytj.ebook.entity.CategoryEntity;
import com.logytj.ebook.entity.RootCategoryEntity;
import com.logytj.ebook.service.CategoryService;
import com.logytj.ebook.vo.CategoryVO;
import com.logytj.ebook.vo.MoreBookVO;

@Service
public class CategoryServiceImp implements CategoryService {

	@Autowired
	private RootCategoryDao rootDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private StyleDao sysDao;
	
	@Override
	public List<CategoryVO> rootCategoryls() {
		// TODO Auto-generated method stub
		List<RootCategoryEntity> list = rootDao.selectRootCategory();
		List<CategoryVO> volist = new ArrayList<>();
		CategoryVO  o = new CategoryVO();
		o.setId(-1);
		o.setName("全部");
		o.setIs_active(true);
		volist.add(o);
		list.forEach(e ->{
			CategoryVO  vo = new CategoryVO();
			vo.setId(e.getRootId());
			vo.setName(e.getRootSex()+"生分类");
			vo.setIs_active(false);
			volist.add(vo);
		});
		return volist;
	}

	@Override
	public List<CategoryVO> selectiveCategoryls(CategoryEntity category) {
		// TODO Auto-generated method stub
		List<CategoryEntity> list = categoryDao.selectCategoryBySelective(category);
		List<CategoryVO> volist = new ArrayList<>();
		CategoryVO  o = new CategoryVO();
		o.setId(-1);
		o.setName("全部");
		o.setIs_active(true);
		volist.add(o);
		
		for(CategoryEntity e : list) {
			CategoryVO vo = new CategoryVO();
			vo.setId(e.getCategoryId());
			vo.setName(e.getCategoryName());
			vo.setIs_active(false);
			volist.add(vo);
		}
		
		return volist;
	}

	@Override
	public List<MoreBookVO> selectiveBookls(BookEntity book,Integer rootId) {
		// TODO Auto-generated method stub
		List<BookEntity> list = null;
		if((rootId == 0 || rootId == 1) && book.getCategotyId() == -1) {
			List<CategoryEntity> catList = categoryDao.selectCategoryByRootId(rootId);
			list = bookDao.selectBookSelective(catList, book);
		}
		else if(book.getCategotyId() > -1) {
			CategoryEntity category = new CategoryEntity();
			category.setCategoryId(book.getCategotyId());
			list = bookDao.selectBookSelective(Collections.singletonList(category),book);
		}else {
			list = bookDao.selectBookSelective(null, book);
		}
		List<MoreBookVO> voList = new ArrayList<>();
		Iterator<BookEntity> iter = list.iterator();
		
		while(iter.hasNext()) {
			BookEntity e = iter.next();
			MoreBookVO vo = new MoreBookVO();
			vo.setBookId(e.getBookId());
			vo.setBookImg(e.getBookImg());
			vo.setBookName(e.getBookName());
			String temp = e.getIntro();
			if(temp.length() > 75)
				temp = temp.substring(0, 75) + "...";
			vo.setIntro(temp);
			vo.setTabs(sysDao.selectStyleByBookId(e.getBookId(), Constants.TAB_NUM));
			voList.add(vo);
		}
		return voList;
	}

}
