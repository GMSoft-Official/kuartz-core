package com.kuartz.core.common.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KzPage<T> extends PageImpl<T> {
    private static final long serialVersionUID = -6211776190868815697L;



    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public KzPage(@JsonProperty("content") List<T> content,
                    @JsonProperty("number") int page,
                    @JsonProperty("size") int size,
                    @JsonProperty("totalElements") long total) {

        super(content, KzPageable.of(page, size), total);
    }

    public KzPage(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public KzPage(List<T> content,Pageable pageable ) {
        super(content,pageable,content.size());
    }

    public KzPage(List<T> content) {
        super(content);
    }

    public <U> KzPage<U> mapPage(Function<? super T, ? extends U> converter) {
        return new KzPage<>(getConvertedContent(converter),getPageable(),getTotalElements());
    }
}
