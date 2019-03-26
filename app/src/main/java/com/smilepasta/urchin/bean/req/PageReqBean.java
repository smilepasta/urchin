package com.smilepasta.urchin.bean.req;

/**
 * Author: huangxiaoming
 * Date: 2019/3/25
 * Desc:
 * Version: 1.0
 */
public class PageReqBean {

    /**
     * page_index : 1
     * page_size : 5
     */

    private int page_index;
    private int page_size;

    public int getPage_index() {
        return page_index;
    }

    public void setPage_index(int page_index) {
        this.page_index = page_index;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
}
