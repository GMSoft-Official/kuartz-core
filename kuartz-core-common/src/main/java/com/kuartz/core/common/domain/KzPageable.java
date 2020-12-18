package com.kuartz.core.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class KzPageable implements Pageable {

    private static final long serialVersionUID = - 970697255361843356L;

    private KzSortable sort;
    private int page;
    private int size;

    public KzPageable() {
    }

    public KzPageable(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public KzPageable(int page, int size,KzSortable sort) {
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    @JsonIgnore
    @Override
    public int getPageNumber() {
        return page;
    }

    @JsonIgnore
    @Override
    public int getPageSize() {
        return size;
    }

    @JsonIgnore
    @Override
    public long getOffset() {
        return (long) page * (long) size;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @JsonIgnore
    @Override
    public Pageable next() {
        return KzPageable.of(getPageNumber() + 1, getPageSize(), (KzSortable) getSort());
    }

    @JsonIgnore
    @Override
    public Pageable previousOrFirst() {
        return getPageNumber() == 0 ? this : KzPageable.of(getPageNumber() - 1, getPageSize(), (KzSortable) getSort());
    }

    @JsonIgnore
    @Override
    public Pageable first() {
        return KzPageable.of(0, getPageSize(), (KzSortable) getSort());
    }

    @JsonIgnore
    @Override
    public boolean hasPrevious() {
        return page > 0;
    }

    public static KzPageable of(int page, int size) {
        return new KzPageable(page, size);
    }

    public static KzPageable of(int page, int size, KzSortable sort) {
        return new KzPageable(page, size, sort);
    }

    public void setSort(KzSortable sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
