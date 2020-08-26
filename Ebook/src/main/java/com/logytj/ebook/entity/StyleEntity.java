package com.logytj.ebook.entity;

import java.io.Serializable;

/**
*  style
* @author logytj 2020-08-13
*/
public class StyleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * style_id
    */
    private Integer styleId;

    /**
    * book_id
    */
    private Integer bookId;

    /**
    * 标签名
    */
    private String styleName;


    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

}

