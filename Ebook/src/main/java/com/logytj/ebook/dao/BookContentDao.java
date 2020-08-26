package com.logytj.ebook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.logytj.ebook.entity.BookContent;

@Mapper
public interface BookContentDao {

    /**
    * [����]
    * @author logytj
    * @date 2020/08/17
    **/
    int insert(BookContent bookContent);

    /**
    * [�h��]
    * @author logytj
    * @date 2020/08/17
    **/
    int delete(int id);
    
    BookContent selectBookContentByPrimaryKey(Integer bookContentId);
    
    BookContent selectBookContentMinId(Integer bookId);
    
    BookContent selectBookContentMaxId(Integer bookId);
    
    List<BookContent> selectBookContentPage(Map<String,Object> map);
    
    int selectBookContentAll(Map<String,Object> map);
    
}
