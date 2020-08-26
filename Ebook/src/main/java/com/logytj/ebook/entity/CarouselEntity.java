package com.logytj.ebook.entity;
import java.io.Serializable;

/**
*  carousel
* @author logytj 2020-08-13
*/
public class CarouselEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * book_id
    */
    private Integer bookId;
    
    private Integer carouselId;
    
    /**
    * 轮播图片地址url
    */
    private String carouselImg;

    /**
    * carousel_sexchar
    */
    private String carouselSex;

    /**
    * 排序值
    */
    private Integer rank;

    /**
    * is_deleted
    */
    private Integer isDeleted;


  

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    
    public Integer getCarouselId() {
    	return carouselId;
    }
    
    public void setCarouselId(Integer carouselId) {
    	this.carouselId = carouselId;
    }
    
    public String getCarouselImg() {
        return carouselImg;
    }

    public void setCarouselImg(String carouselImg) {
        this.carouselImg = carouselImg;
    }

    public String getCarouselSex() {
        return carouselSex;
    }

    public void setCarouselSex(String carouselSex) {
        this.carouselSex = carouselSex;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
