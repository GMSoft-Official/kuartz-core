package com.kuartz.core.common.domain;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KzPage<T> implements Serializable {
    private static final long serialVersionUID = -6211776190868815697L;

    private final List<T> content = new ArrayList<>();

    private final KzPageable pageable;


    public KzPage(List<T> content, KzPageable pageable) {
        Assert.notNull(content, "Content must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");
        this.content.addAll(content);
        this.pageable = pageable;
    }

    public Integer getPageNumber() {
        return pageable.getPageNumber();
    }

    public Integer getPageSize() {
        return pageable.getPageSize();
    }

    public List<T> getContent() {
        return content;
    }

    public KzPageable getPageable() {
        return pageable;
    }
}
