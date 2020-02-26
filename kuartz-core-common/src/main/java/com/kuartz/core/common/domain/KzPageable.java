package com.kuartz.core.common.domain;

import java.io.Serializable;

public class KzPageable implements Serializable {

    private static final long serialVersionUID = -970697255361843356L;

    private KzSort sort;

    private Integer pageNumber;

    private Integer pageSize;

    public KzPageable() {
        //    bos yapici
    }

    public KzPageable(KzSort sort, Integer pageNumber, Integer pageSize) {
        this.sort       = sort;
        this.pageNumber = pageNumber;
        this.pageSize   = pageSize;
    }

    public KzPageable(KzSort sort, Integer pageNumber) {
        this.sort       = sort;
        this.pageNumber = pageNumber;
    }

    public KzPageable(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize   = pageSize;
    }

    public KzSort getSort() {
        return sort;
    }

    public void setSort(KzSort sort) {
        this.sort = sort;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
