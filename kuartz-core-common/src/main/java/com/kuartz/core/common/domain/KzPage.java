package com.kuartz.core.common.domain;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class KzPage<T> extends PageImpl<T> {
    private static final long serialVersionUID = -6211776190868815697L;

    public KzPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public KzPage(List<T> content) {
        super(content);
    }
}
