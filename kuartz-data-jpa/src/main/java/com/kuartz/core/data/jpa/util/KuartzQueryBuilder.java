package com.kuartz.core.data.jpa.util;

import com.kuartz.core.common.model.KuartzQueryModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.EntityPathBase;

public abstract class KuartzQueryBuilder<QM extends KuartzQueryModel, EQ extends EntityPathBase> {

    protected abstract void prepareQuery(BooleanBuilder builder, QM queryModel, EQ entityQuery);

    public BooleanBuilder buildQuery(QM querymodel, EQ entityQuery) {
        BooleanBuilder builder = new BooleanBuilder();
        if (querymodel != null) {
            prepareQuery(builder, querymodel, entityQuery);
        }
        return builder;
    }
}
