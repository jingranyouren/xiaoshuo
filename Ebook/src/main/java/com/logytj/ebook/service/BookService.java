package com.logytj.ebook.service;

import java.util.List;

import com.logytj.ebook.common.PageResult;
import com.logytj.ebook.common.PageUtils;
import com.logytj.ebook.entity.AddBookshellEntity;
import com.logytj.ebook.vo.BookContentVO;
import com.logytj.ebook.vo.BookInfoVO;
import com.logytj.ebook.vo.BookVO;
import com.logytj.ebook.vo.MoreBookVO;
/**
 * 
 * @author logytj
 *
 */
public interface BookService {
		List<BookVO> getBookForShell(Integer userId);
		
		List<BookVO> getBookForNotes(Integer userId);
		
		BookInfoVO getBookInfo(Integer bookId,Integer userId);
		
		BookContentVO getBookContent(Integer userId,Integer bookId);
		
		PageResult getBookContentList(PageUtils page,Integer BookContentId);
		
		BookContentVO getBookContent(Integer bookContentId);
		
		String removeShell(AddBookshellEntity shell);
		
		String putShell(AddBookshellEntity shell);
		
		List<MoreBookVO> getBookByStatus(Integer freeStatus);
		
		List<MoreBookVO> getBookByUpdate(Integer updateStatus);
		
		List<MoreBookVO> searchBook(String keyword);
		
		void saveReadNotes(Integer userId,Integer bookId,Integer bookContentId);
}
