package com.logytj.ebook.serviceImp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logytj.ebook.common.Constants;
import com.logytj.ebook.dao.BookDao;
import com.logytj.ebook.dao.IndexConfigDao;
import com.logytj.ebook.dao.StyleDao;
import com.logytj.ebook.entity.BookEntity;
import com.logytj.ebook.entity.IndexConfigEntity;
import com.logytj.ebook.service.IndexConfigSevice;
import com.logytj.ebook.vo.BookVO;
import com.logytj.ebook.vo.IndexCofigVO;
import com.logytj.ebook.vo.MoreBookVO;

@Service
public class IndexConfigServiceImp implements IndexConfigSevice {

	@Autowired
	private IndexConfigDao indexConfigDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private StyleDao styDao;
	@Override
	public List<IndexCofigVO> getItemForIndex(String sex) {
		// TODO Auto-generated method stub
		List<IndexCofigVO> voList = new ArrayList<>();
		for(int i = 1 ; i < 5 ; i++) {
			IndexCofigVO vo = new IndexCofigVO();
			vo.setConfigType(i);
			switch(i) {
			case 1:
				vo.setConfigName("本周热门");
				break;
			case 2:
				vo.setConfigName("新书抢鲜");
				break;
			case 3:
				vo.setConfigName("精选小说");
				break;
			case 4:
				vo.setConfigName("猜你喜欢");
				break;
			}
			vo.setConfigSex(sex);
			List<IndexConfigEntity> indexlist =  indexConfigDao.selectIndexConfigByTypeAndSex(i, sex,Constants.INDEX_ITEM_NUM);
			List<BookVO> bookVOList = new ArrayList<>();
			indexlist.forEach(e ->{
				BookVO bookVO = new BookVO();
				BookEntity book = bookDao.selectBookByPrimaryKey(e.getBookId());
				bookVO.setBookId(book.getBookId());
				bookVO.setBookImg(book.getBookImg());
				bookVO.setBookName(book.getBookName());
				bookVOList.add(bookVO);
			});
			vo.setList(bookVOList);
			voList.add(vo);
		}
		return voList;
	}
	@Override
	public List<MoreBookVO> getMorebyConfigTypeAndSex(Integer configType, String sex) {
		// TODO Auto-generated method stub
		List<IndexConfigEntity> indexlist =  indexConfigDao.selectIndexConfigByTypeAndSex(configType, sex,0);
		List<MoreBookVO> voList = new ArrayList<>();
		Iterator<IndexConfigEntity> it = indexlist.iterator();
		while(it.hasNext()) {
			IndexConfigEntity e = it.next();
			BookEntity book = bookDao.selectBookByPrimaryKey(e.getBookId());
			MoreBookVO  vo = new MoreBookVO();
			vo.setBookId(e.getBookId());
			vo.setBookImg(book.getBookImg());
			vo.setBookName(book.getBookName());
			String temp = book.getIntro();
			if(temp.length() > 75)
				temp = temp.substring(0, 75) + "...";
			vo.setIntro(temp);
			vo.setTabs(styDao.selectStyleByBookId(e.getBookId(), Constants.TAB_NUM));
			voList.add(vo);
		}
		return voList;
	}

}
