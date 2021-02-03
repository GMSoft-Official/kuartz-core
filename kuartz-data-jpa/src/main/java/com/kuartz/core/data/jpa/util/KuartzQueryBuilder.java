package com.kuartz.core.data.jpa.util;

import com.kuartz.core.common.model.KuartzQueryModel;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.entity.query.KuartzEntityQuery;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;

public abstract class KuartzQueryBuilder<QM extends KuartzQueryModel, EQ extends EntityPathBase<? extends KuartzEntity>> {

    protected abstract void prepareQuery(BooleanBuilder builder, QM queryModel, EQ entityQuery);

    public BooleanBuilder buildQuery(QM querymodel, EQ entityQuery) {
        BooleanBuilder builder = new BooleanBuilder();
        if (querymodel != null) {
            final PathBuilder<? extends KuartzEntity> pathBuilder = new PathBuilder<>(entityQuery.getType(), entityQuery.getMetadata());
            builder.and(pathBuilder.get(KuartzEntity.DELETED_FIELD).eq(querymodel.getDeleted()));
            prepareQuery(builder, querymodel, entityQuery);
        }
        return builder;
    }
}
