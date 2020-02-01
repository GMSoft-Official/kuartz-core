package com.kuartz.core.common.domain;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KzPage<T> implements Serializable {
    private static final long serialVersionUID = -6211776190868815697L;

    private final List<T> content = new ArrayList<>();

    private Long totalElements;

    private Integer totalPage;

    private final KzPageable pageable;

    public KzPage(List<T> content, KzPageable pageable) {
        Assert.notNull(content, "Content must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");
        this.content.addAll(content);
        this.pageable = pageable;
    }

    public KzPage(List<T> content, KzPageable pageable, Long totalElements) {
        this(content, pageable);
        this.totalElements = totalElements;
    }

    public KzPage(List<T> content, KzPageable pageable, Long totalElements, Integer totalPage) {
        this(content, pageable);
        this.totalElements = totalElements;
        this.totalPage     = totalPage;
    }

    public List<T> getContent() {
        return content;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public KzPageable getPageable() {
        return pageable;
    }
}
