package com.kuartz.core.common.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class KzPageable extends PageRequest {

    private static final long serialVersionUID = -970697255361843356L;

    protected KzPageable(int page, int size) {
        super(page, size);
    }

    protected KzPageable(int page, int size, Sort.Direction direction, String... properties) {
        super(page, size, direction, properties);
    }

    protected KzPageable(int page, int size, Sort sort) {
        super(page, size, sort);
    }


    /**
     * Creates a new unsorted {@link PageRequest}.
     *
     * @param page zero-based page index.
     * @param size the size of the page to be returned.
     *
     * @since 2.0
     */
    public static KzPageable of(int page, int size) {
        return of(page, size, Sort.unsorted());
    }

    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param page zero-based page index.
     * @param size the size of the page to be returned.
     * @param sort must not be {@literal null}.
     *
     * @since 2.0
     */
    public static KzPageable of(int page, int size, Sort sort) {
        return new KzPageable(page, size, sort);
    }

    /**
     * Creates a new {@link PageRequest} with sort direction and properties applied.
     *
     * @param page       zero-based page index.
     * @param size       the size of the page to be returned.
     * @param direction  must not be {@literal null}.
     * @param properties must not be {@literal null}.
     *
     * @since 2.0
     */
    public static KzPageable of(int page, int size, Sort.Direction direction, String... properties) {
        return of(page, size, Sort.by(direction, properties));
    }
}
