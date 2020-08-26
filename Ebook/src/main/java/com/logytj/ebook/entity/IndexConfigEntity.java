package com.logytj.ebook.entity;

import java.io.Serializable;


/**
*  index_config
* @author logytj 2020-08-13
*/
public class IndexConfigEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * book_id
    */
    private Integer bookId;

    /**
    * config_id
    */
    private Integer configId;

    /**
    * 性别 check (config_sex in( 男 ， 女 ))
    */
    private String configSex;

    /**
    * 0 轮播图配置; 1 本周热门; 2 新书抢鲜；3 精选小说； 4 猜你喜欢 check(config_type in(0，1，2，3，4))
    */
    private Integer configType;

    /**
    * rank
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

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigSex() {
        return configSex;
    }

    public void setConfigSex(String configSex) {
        this.configSex = configSex;
    }

    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
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
