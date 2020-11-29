package com.kuartz.core.data.jpa.util;

import com.kuartz.core.common.domain.KzPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.LongSupplier;

/**
 * @author Kutay Celebi
 * @since 13.11.2020 00:08
 */
public class ExecutionUtils {

    /**
     * Constructs a {@link Page} based on the given {@code content}, {@link Pageable} and {@link Supplier} applying
     * optimizations. The construction of {@link Page} omits a count query if the total can be determined based on the
     * result size and {@link Pageable}.
     *
     * @param content must not be {@literal null}.
     * @param pageable must not be {@literal null}.
     * @param totalSupplier must not be {@literal null}.
     * @return the {@link Page}.
     */
    public static <T> KzPage<T> getPage(List<T> content, Pageable pageable, LongSupplier totalSupplier) {

        Assert.notNull(content, "Content must not be null!");
        Assert.notNull(pageable, "Pageable must not be null!");
        Assert.notNull(totalSupplier, "TotalSupplier must not be null!");

        if (pageable.isUnpaged() || pageable.getOffset() == 0) {

            if (pageable.isUnpaged() || pageable.getPageSize() > content.size()) {
                return new KzPage<>(content, pageable, content.size());
            }

            return new KzPage<>(content, pageable, totalSupplier.getAsLong());
        }

        if (content.size() != 0 && pageable.getPageSize() > content.size()) {
            return new KzPage<>(content, pageable, pageable.getOffset() + content.size());
        }

        return new KzPage<>(content, pageable, totalSupplier.getAsLong());
    }

}
