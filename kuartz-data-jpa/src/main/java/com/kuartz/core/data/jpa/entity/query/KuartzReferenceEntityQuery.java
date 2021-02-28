package com.kuartz.core.data.jpa.entity.query;

import static com.querydsl.core.types.PathMetadataFactory.*;
import com.kuartz.core.data.jpa.entity.KuartzReferenceEntity;


import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * KuartzReferenceEntityQuery is a Querydsl query type for KuartzReferenceEntity
 */
@Generated("com.querydsl.codegen.SupertypeSerializer")
public class KuartzReferenceEntityQuery extends EntityPathBase<KuartzReferenceEntity> {

    private static final long serialVersionUID = -179672086L;

    public static final KuartzReferenceEntityQuery kuartzReferenceEntity = new KuartzReferenceEntityQuery("kuartzReferenceEntity");

    public final KuartzEntityQuery _super = new KuartzEntityQuery(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final DateTimePath<java.util.Date> deletedAt = _super.deletedAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final NumberPath<Long> referenceVersion = createNumber("referenceVersion", Long.class);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath uuid = _super.uuid;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public KuartzReferenceEntityQuery(String variable) {
        super(KuartzReferenceEntity.class, forVariable(variable));
    }

    public KuartzReferenceEntityQuery(Path<? extends KuartzReferenceEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public KuartzReferenceEntityQuery(PathMetadata metadata) {
        super(KuartzReferenceEntity.class, metadata);
    }

}

