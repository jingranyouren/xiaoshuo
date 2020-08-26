package com.logytj.ebook.entity;
import java.io.Serializable;

/**
*  book_content
* @author logytj 2020-08-17
*/
public class BookContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * ���
    */
    private Integer bookId;

    /**
    * �½�id
    */
    private Integer bookContentId;

    /**
    * �½�����·��
    */
    private String bookContentPath;

    private String bookContentName;
    public String getBookContentName() {
    	return bookContentName;
    }
    public void setBookContentName(String bookContentName) {
    	this.bookContentName = bookContentName;
    }
    public BookContent() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookContentId() {
        return bookContentId;
    }

    public void setBookContentId(Integer bookContentId) {
        this.bookContentId = bookContentId;
    }

    public String getBookContentPath() {
        return bookContentPath;
    }

    public void setBookContentPath(String bookContentPath) {
        this.bookContentPath = bookContentPath;
    }

}
