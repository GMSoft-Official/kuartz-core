package com.kuartz.core.common.domain;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class KzPageable extends AbstractPageRequest {

    // todo KZ sort yazalım
    private Sort sort;

    /**
     * Creates a new {@link AbstractPageRequest}. Pages are zero indexed, thus providing 0 for {@code page} will return
     * the first page.
     *
     * @param page must not be less than zero.
     * @param size must not be less than one.
     */
    public KzPageable(int page, int size) {
        super(page, size);
    }

    public KzPageable(int page, int size, Sort sort) {
        super(page, size);
        this.sort = sort;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new KzPageable(getPageNumber() + 1, getPageSize(), sort);
    }

    @Override
    public Pageable previous() {
        return new KzPageable(getPageNumber() - 1, getPageSize(), sort);
    }

    @Override
    public Pageable first() {
        return new KzPageable(0, getPageSize(), sort);
    }
}
