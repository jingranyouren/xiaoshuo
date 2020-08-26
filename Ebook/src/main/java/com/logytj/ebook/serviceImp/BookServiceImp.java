package com.logytj.ebook.serviceImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logytj.ebook.common.Constants;
import com.logytj.ebook.common.PageResult;
import com.logytj.ebook.common.PageUtils;
import com.logytj.ebook.dao.AddBookshellDao;
import com.logytj.ebook.dao.BookContentDao;
import com.logytj.ebook.dao.BookDao;
import com.logytj.ebook.dao.DiscussDao;
import com.logytj.ebook.dao.ReadNotesDao;
import com.logytj.ebook.dao.StyleDao;
import com.logytj.ebook.entity.AddBookshellEntity;
import com.logytj.ebook.entity.BookContent;
import com.logytj.ebook.entity.BookEntity;
import com.logytj.ebook.entity.ReadNotesEntity;
import com.logytj.ebook.service.BookService;
import com.logytj.ebook.utils.ReadFileUtils;
import com.logytj.ebook.vo.BookContentVO;
import com.logytj.ebook.vo.BookInfoVO;
import com.logytj.ebook.vo.BookVO;
import com.logytj.ebook.vo.MoreBookVO;

@Service
public class BookServiceImp implements BookService {

	@Autowired
	private AddBookshellDao shellDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private ReadNotesDao notesDao;
	@Autowired
	private DiscussDao disDao;
	@Autowired
	private StyleDao sysDao;
	@Autowired
	private BookContentDao contentDao;
	@Override
	public List<BookVO> getBookForShell(Integer userId) {
		// TODO Auto-generated method stub
		List<BookVO> voList = new ArrayList<>();
		List<AddBookshellEntity> shellList = shellDao.selectBookShellByUserId(userId,Constants.BOOK_SHELL_NUM);
		shellList.forEach(e ->{
			BookVO vo = new BookVO();
			BookEntity book = bookDao.selectBookByPrimaryKey(e.getBookId());
			vo.setBookId(book.getBookId());
			vo.setBookImg(book.getBookImg());
			vo.setBookName(book.getBookName());
			voList.add(vo);
		});
		return voList;
	}
	@Override
	public List<BookVO> getBookForNotes(Integer userId) {
		// TODO Auto-generated method stub
		List<BookVO> voList = new ArrayList<>();
		List<ReadNotesEntity> shellList = notesDao.selectNotesByUserId(userId,Constants.BOOK_SHELL_NUM);
		shellList.forEach(e ->{
			BookVO vo = new BookVO();
			BookEntity book = bookDao.selectBookByPrimaryKey(e.getBookId());
			vo.setBookId(book.getBookId());
			vo.setBookImg(book.getBookImg());
			vo.setBookName(book.getBookName());
			voList.add(vo);
		});
		return voList;
	}
	@Override
	public BookInfoVO getBookInfo(Integer bookId, Integer userId) {
		// TODO Auto-generated method stub
		BookInfoVO vo = new BookInfoVO();
		BookEntity book = bookDao.selectBookByPrimaryKey(bookId);
		vo.setAuthor(book.getAuthor());
		vo.setBookImg(book.getBookImg());
		vo.setBookName(book.getBookName());
		vo.setIntro(book.getIntro());
		vo.setStatus(book.getUpdateStatus() == 0 ? "连载中" : "完结");
		vo.setWordNum(book.getWordNum());
		vo.setDisList(disDao.selectDiscussByBookId(bookId, Constants.DISCUSS_NUM));
		vo.setTabs(sysDao.selectStyleByBookId(bookId, Constants.TAB_NUM));
		AddBookshellEntity shell  = shellDao.selectAddBookshellByPrimaryKey(userId, bookId);
		if(shell == null)
			vo.setIsInShell(false);
		else {
			boolean flag = false;
			List<AddBookshellEntity> shellList = shellDao.selectBookShellByUserId(userId,Constants.BOOK_SHELL_NUM);
			int size = shellList.size();
			int i = 0;
			for( ; i < size; i++) {
				if(shellList.get(i).equals(shell))
					break;
			}
			if(i < size)
				flag = true;
			vo.setIsInShell(flag);
		}
		BookContent bc = contentDao.selectBookContentMinId(bookId);
		vo.setFirstCName(bc.getBookContentName());
		bc = contentDao.selectBookContentMaxId(bookId);
		vo.setLastCName(bc.getBookContentName());
		Map<String,Object> map = new HashMap<>();
		map.put("bookId",bookId);
		int count = contentDao.selectBookContentAll(map);
		vo.setCount(count);
		return vo;
	}
	@Override
	public BookContentVO getBookContent(Integer userId, Integer bookId) {
		// TODO Auto-generated method stub
		BookContent content = null;
		BookContentVO contentvo = new BookContentVO();
		ReadNotesEntity notes = notesDao.selectNotesByBookIdAndUserID(bookId, userId);
		
		BookEntity book = bookDao.selectBookByPrimaryKey(bookId);
		//阅读记录中没有，获取第一章
		if(notes == null) {
			content = contentDao.selectBookContentMinId(bookId);
		}else {
			content = contentDao.selectBookContentByPrimaryKey(notes.getLastReadContentId());
		}
		if(content != null) {
			contentvo.setBookContentId(content.getBookContentId());
			contentvo.setBookContent(ReadFileUtils.readFile(content.getBookContentPath()));
			contentvo.setBookContentName(content.getBookContentName());
			contentvo.setBookName(book.getBookName());
		}
		return contentvo;
	}
	@Override
	public PageResult getBookContentList(PageUtils page,Integer BookContentId) {
		// TODO Auto-generated method stub
		int all = contentDao.selectBookContentAll(page);
		List<BookContent> list = contentDao.selectBookContentPage(page);
		PageResult result = new PageResult(list,page.getPage(),page.getLimint(),all);
		if(BookContentId == null) {
			//return result;
		}else {
			int j = 0;
			for( ; j < list.size(); j++) {
				if(BookContentId.equals(list.get(j).getBookContentId()))
					break;
			}
			if(j < list.size()) {
				//return result;
				;
			}
			else {
				int pageCount = result.getAllPage();
				for(int i = 2; i <= pageCount; i++ ) {
					page.put("page", i);
					page = new PageUtils(page);
					list = contentDao.selectBookContentPage(page);
					result = new PageResult(list,page.getPage(),page.getLimint(),all);
					int k = 0;
					for( ; k < list.size(); k++) {
						if(BookContentId.equals(list.get(k).getBookContentId()))
							break;
					}
					if(k < list.size())
						break;
				}
			}
		}
		//章节名过长影响美观，处理一下
		if(result != null) {
			result.getList().forEach(e->{
				String temp = ((BookContent)e).getBookContentName();
				if(temp.length() > 15)
					((BookContent)e).setBookContentName(temp.substring(0, 15)+"...");
				((BookContent)e).setBookContentPath("");
			});
		}
		return result;
	}
	@Override
	public BookContentVO getBookContent(Integer bookContentId) {
		// TODO Auto-generated method stub
		BookContentVO vo = null;
		BookContent bc = contentDao.selectBookContentByPrimaryKey(bookContentId);
		if(bc != null) {
			vo =  new BookContentVO();
			vo.setBookContent(ReadFileUtils.readFile(bc.getBookContentPath()));
			vo.setBookContentId(bookContentId);
			vo.setBookContentName(bc.getBookContentName());
			
			String bookName = bookDao.selectBookByPrimaryKey(bc.getBookId()).getBookName();
			vo.setBookName(bookName);
		}
		return vo;
	}
	@Override
	public String removeShell(AddBookshellEntity shell) {
		// TODO Auto-generated method stub
		int row = shellDao.deleteShellByprimaryKey(shell.getUserId(), shell.getBookId());
		if(row > 0)
			return "success";
		return "failed";
	}
	@Override
	public String putShell(AddBookshellEntity shell) {
		// TODO Auto-generated method stub
		shell.setAddTime(new Date());
		int row = shellDao.insertSelective(shell);
		if(row > 0)
			return "success";
		return "failed";
	}
	@Override
	public List<MoreBookVO> getBookByStatus(Integer freeStatus) {
		// TODO Auto-generated method stub
		List<BookEntity> list = bookDao.selectBooKByFreeStatus(freeStatus);
		List<MoreBookVO> voList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			 BookEntity e = list.get(i);
			 MoreBookVO vo = new MoreBookVO();
			 vo.setBookId(e.getBookId());
			 vo.setBookImg(e.getBookImg());
			 
			 vo.setBookName(e.getBookName());
			 String temp = e.getIntro();
			 if(temp.length() > 75)
				 temp = temp.substring(0,75)+"...";
			
			 vo.setIntro(temp);
			 vo.setTabs(sysDao.selectStyleByBookId(e.getBookId(),Constants.TAB_NUM));
			 voList.add(vo);
		}
		return voList;
	}
	@Override
	public List<MoreBookVO> getBookByUpdate(Integer updateStatus) {
		// TODO Auto-generated method stub
		List<BookEntity> list = bookDao.selectBookByupdateStatus(updateStatus);
		List<MoreBookVO> voList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			 BookEntity e = list.get(i);
			 MoreBookVO vo = new MoreBookVO();
			 vo.setBookId(e.getBookId());
			 vo.setBookImg(e.getBookImg());
			 
			 vo.setBookName(e.getBookName());
			 String temp = e.getIntro();
			 if(temp.length() > 75)
				 temp = temp.substring(0,75)+"...";
			
			 vo.setIntro(temp);
			 vo.setTabs(sysDao.selectStyleByBookId(e.getBookId(),Constants.TAB_NUM));
			 voList.add(vo);
		}
		return voList;
	}
	@Override
	public List<MoreBookVO> searchBook(String keyword) {
		// TODO Auto-generated method stub
		List<BookEntity> list = bookDao.selectBookByKeyword(keyword);
		List<MoreBookVO> voList = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++) {
			 BookEntity e = list.get(i);
			 MoreBookVO vo = new MoreBookVO();
			 vo.setBookId(e.getBookId());
			 vo.setBookImg(e.getBookImg());
			 
			 vo.setBookName(e.getBookName());
			 String temp = e.getIntro();
			 if(temp.length() > 75)
				 temp = temp.substring(0,75)+"...";
			
			 vo.setIntro(temp);
			 vo.setTabs(sysDao.selectStyleByBookId(e.getBookId(),Constants.TAB_NUM));
			 voList.add(vo);
		}
		return voList;
	}
	@Override
	public void saveReadNotes(Integer userId, Integer bookId,Integer bookContenId) {
		// TODO Auto-generated method stub
		ReadNotesEntity notes = notesDao.selectNotesByBookIdAndUserID(bookId, userId);
		if(notes == null) {
			ReadNotesEntity temp = new ReadNotesEntity();
			temp.setBookId(bookId);
			temp.setUserId(userId);
			temp.setLastReadTime(new Date());
			temp.setLastReadContentId(bookContenId);
			notesDao.insertBySelective(temp);
		}
		else {
			ReadNotesEntity temp = new ReadNotesEntity();
			temp.setBookId(bookId);
			temp.setUserId(userId);
			temp.setLastReadTime(new Date());
			temp.setLastReadContentId(bookContenId);
			notesDao.updateLast(temp);
		}
	}
	
}
