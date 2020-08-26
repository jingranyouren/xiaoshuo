package com.logytj.ebook.entity;

import java.io.Serializable;
/**
*  category
* @author logytj 2020-08-13
*/
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * root_id
    */
    private Integer rootId;
    
    private Integer categoryId;
    /**
    * category_name
    */
    private String categoryName;

    /**
    * rank
    */
    private Integer rank;

    /**
    * is_deleted
    */
    private Integer isDeleted;



    public Integer getCategoryId() {
    	return categoryId;
    }
    
    public void setCategoryId(Integer categoryId) {
    	this.categoryId = categoryId;
    }
    
    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
