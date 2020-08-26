package com.logytj.ebook.entity;

import java.io.Serializable;


/**
*  root_category
* @author logytj 2020-08-13
*/
public class RootCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * root_id
    */
    private Integer rootId;

    /**
    * root_sex
    */
    private String rootSex;


  

    public Integer getRootId() {
        return rootId;
    }

    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public String getRootSex() {
        return rootSex;
    }

    public void setRootSex(String rootSex) {
        this.rootSex = rootSex;
    }

}

