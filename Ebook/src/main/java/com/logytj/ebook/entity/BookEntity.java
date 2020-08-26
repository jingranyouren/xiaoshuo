package com.logytj.ebook.entity;

import java.io.Serializable;
import java.util.Date;

/**
*  book
* @author logytj 2020-08-13
*/
public class BookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * book_id
    */
    private Integer bookId;

    /**
    * book_name
    */
    private String bookName;

    /**
    * 小说图片存放路径 url
    */
    private String bookImg;

    /**
    * author
    */
    private String author;

    /**
    * 小说总字数 单位 万字
    */
    private Float wordNum;

    /**
    * 小说的状态 0 连载中 ；1 完结
    */
    private Integer updateStatus;

    /**
    * intro
    */
    private String intro;

    /**
    * 小说内容存放路径 url
    */
    private String bookFilePath;

    /**
    * 免费状态 ， 0 免费 1 收费 2 限时免费 check(free_status in (0，1，2))
    */
    private Integer freeStatus;

    /**
    * 限时免费开始时间
    */
    private Date freeStartTime;

    /**
    * 限时免费结束时间
    */
    private Date freeEndTime;

    /**
    * 小说分类 id
    */
    private Integer categotyId;


    public BookEntity() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getWordNum() {
        return wordNum;
    }

    public void setWordNum(Float wordNum) {
        this.wordNum = wordNum;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getBookFilePath() {
        return bookFilePath;
    }

    public void setBookFilePath(String bookFilePath) {
        this.bookFilePath = bookFilePath;
    }

    public Integer getFreeStatus() {
        return freeStatus;
    }

    public void setFreeStatus(Integer freeStatus) {
        this.freeStatus = freeStatus;
    }

    public Date getFreeStartTime() {
        return freeStartTime;
    }

    public void setFreeStartTime(Date freeStartTime) {
        this.freeStartTime = freeStartTime;
    }

    public Date getFreeEndTime() {
        return freeEndTime;
    }

    public void setFreeEndTime(Date freeEndTime) {
        this.freeEndTime = freeEndTime;
    }

    public Integer getCategotyId() {
        return categotyId;
    }

    public void setCategotyId(Integer categotyId) {
        this.categotyId = categotyId;
    }

}
