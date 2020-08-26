package com.logytj.ebook.reader.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logytj.ebook.common.Constants;
import com.logytj.ebook.common.PageUtils;
import com.logytj.ebook.common.Result;
import com.logytj.ebook.common.ResultGenerator;
import com.logytj.ebook.entity.AddBookshellEntity;
import com.logytj.ebook.service.BookService;
import com.logytj.ebook.vo.BookContentVO;

@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/bookShell/{userId}")
	public Result bookShell(@PathVariable("userId") Integer userId) {
		return ResultGenerator.genSuccess(bookService.getBookForShell(userId));
	}
	
	@GetMapping("/readNotes/{userId}")
	public Result readNotes(@PathVariable("userId") Integer userId) {
		return ResultGenerator.genSuccess(bookService.getBookForNotes(userId));
	}
	
	@GetMapping("/bookInfo/{bookId}")
	public Result bookInfo(@PathVariable("bookId") Integer bookId,@RequestParam("userId") Integer userId) {
		return ResultGenerator.genSuccess(bookService.getBookInfo(bookId, userId));
	}
	
	@GetMapping("/read/{bookId}/{userId}")
	public Result readContent(@PathVariable("bookId")Integer bookId,
			@PathVariable("userId")Integer userId) {
		return ResultGenerator.genSuccess(bookService.getBookContent(userId, bookId));
	}
	
	@GetMapping("/capter/{bookId}")
	public Result Capter(@PathVariable("bookId") Integer bookId,HttpServletRequest request) {
		String str = request.getParameter("page");
		String id = request.getParameter("bookContentId");
		if(str != null) {
			Integer page = Integer.parseInt(str);
			if(page < 1)
				return ResultGenerator.genFailed("参数错误");
			if(id != null && !"".equals(id.trim())) {
				Integer bookContentId =  Integer.parseInt(id);
				Map<String,Object> param = new HashMap<>();
				param.put("page", page);
				param.put("bookId",bookId);
				param.put("limit", Constants.MULV_NUM);
				return ResultGenerator.genSuccess(bookService.getBookContentList(new PageUtils(param),bookContentId));
			}else {
				Map<String,Object> param = new HashMap<>();
				param.put("page", page);
				param.put("bookId",bookId);
				param.put("limit", Constants.MULV_NUM);
				return ResultGenerator.genSuccess(bookService.getBookContentList(new PageUtils(param),null));
			}
		}else {
			Integer page = 1;
			if(id != null && !"".equals(id.trim())) {
				Integer bookContentId =  Integer.parseInt(id);
				Map<String,Object> param = new HashMap<>();
				param.put("page", page);
				param.put("bookId",bookId);
				param.put("limit", Constants.MULV_NUM);
				return ResultGenerator.genSuccess(bookService.getBookContentList(new PageUtils(param),bookContentId));
			}else {
				Map<String,Object> param = new HashMap<>();
				param.put("page", page);
				param.put("bookId",bookId);
				param.put("limit", Constants.MULV_NUM);
				return ResultGenerator.genSuccess(bookService.getBookContentList(new PageUtils(param),null));
			}
		}
	}
	
	@GetMapping("/reader/{bookContentId}")
	public Result read(@PathVariable("bookContentId")Integer bookContentId) {
		BookContentVO content = bookService.getBookContent(bookContentId);
		if(content == null)
			return ResultGenerator.genFailed();
		return ResultGenerator.genSuccess(content);
	}
	@PostMapping("/bookShell/add")
	public Result addShell(@RequestBody AddBookshellEntity shell) {
		String result = bookService.putShell(shell);
		if("success".equals(result))
			return ResultGenerator.genSuccess();
		return ResultGenerator.genFailed();
	}
	
	@PostMapping("/bookShell/remove")
	public Result removeShell(@RequestBody AddBookshellEntity shell) {
		String result = bookService.removeShell(shell);
		if("success".equals(result))
			return ResultGenerator.genSuccess();
		return ResultGenerator.genFailed();
	}
	
	@GetMapping("/book/free")
	public Result bookStatus() {
		return ResultGenerator.genSuccess(bookService.getBookByStatus(0));
	}
	
	@GetMapping("/book/finish")
	public Result resultbookFinish() {
		return ResultGenerator.genSuccess(bookService.getBookByUpdate(1));
	}
	
	@GetMapping("/book/search")
	public Result searchBook(@RequestParam("keyword")String keyword) {
		return ResultGenerator.genSuccess(bookService.searchBook(keyword));
	}
	
	@GetMapping("/saveReadNotes")
	public void saveReadNotes(@RequestParam("bookId")Integer bookId,
							  @RequestParam("userId")Integer userId,
							  @RequestParam("bookContentId")Integer bookContentId){
		bookService.saveReadNotes(userId, bookId, bookContentId);
	}
}
