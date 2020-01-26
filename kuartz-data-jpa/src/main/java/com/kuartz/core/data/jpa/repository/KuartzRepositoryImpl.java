package com.kuartz.core.data.jpa.repository;

import com.kuartz.core.data.jpa.bean.KuartzEntityPathResolver;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QSort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kutay Celebi
 * @since 24.09.2019
 */
@NoRepositoryBean
public class KuartzRepositoryImpl<KE extends KuartzEntity> extends SimpleJpaRepository<KE, Long> implements
        KuartzRepository<KE>, QuerydslPredicateExecutor<KE> {

    private static final KuartzEntityPathResolver PATH_RESOLVER = KuartzEntityPathResolver.INSTANCE;

    private       JpaEntityInformation<KE, ?> entityInformation;
    private final EntityManager               em;
    private final EntityPath<KE>              path;
    private final PathBuilder<KE>             builder;
    private final Querydsl                    querydsl;

    public KuartzRepositoryImpl(JpaEntityInformation<KE, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.entityInformation = entityInformation;
        this.path              = PATH_RESOLVER.createPath(entityInformation.getJavaType());
        this.builder           = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl          = new Querydsl(em, builder);
        this.em                = em;
    }

    public EntityManager getEntityManager() {
        return this.em;
    }

    public JPAQuery<KE> getJpaQuery() {
        JPAQuery<KE> jpaQuery = new JPAQuery<>(em);
        jpaQuery.from(this.path);
        return jpaQuery;
    }

    @Override
    public <S extends KE> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public KE saveFlush(KE entity) {
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            em.flush();
            return entity;
        } else {
            KE merge = em.merge(entity);
            em.flush();
            return merge;
        }
    }

    public KE update(KE entity) {
        return save(entity);
    }

    public KE updateFlush(KE entity) {
        return saveFlush(entity);
    }

    @Override
    public <S extends KE> List<S> saveAll(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        List<S> result = new ArrayList<S>();
        for (S entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    public List<KE> saveAllFlush(Iterable<KE> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        List<KE> result = new ArrayList<KE>();
        for (KE entity : entities) {
            result.add(saveFlush(entity));
        }
        return result;
    }

    @Override
    public Optional<KE> findOne(Predicate predicate) {
        try {
            return Optional.ofNullable(createQuery(predicate).select(path).fetchOne());
        } catch (NonUniqueResultException ex) {
            throw new IncorrectResultSizeDataAccessException(ex.getMessage(), 1, ex);
        }
    }

    @Override
    public Iterable<KE> findAll(Predicate predicate) {
        return createQuery(predicate).fetch();
    }

    @Override
    public Iterable<KE> findAll(Predicate predicate, Sort sort) {
        JPAQuery<KE> query = createQuery(predicate);
        return executeSorted(query, sort);
    }

    @Override
    public Iterable<KE> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        JPAQuery<KE> query = createQuery(predicate);
        return executeSorted(query, orders);
    }

    @Override
    public Iterable<KE> findAll(OrderSpecifier<?>... orders) {

        Assert.notNull(orders, "Order specifiers must not be null!");
        return executeSorted(createQuery(new Predicate[0]).select(path), orders);
    }

    @Override
    public Page<KE> findAll(Predicate predicate, Pageable pageable) {

        Assert.notNull(pageable, "Pageable must not be null!");

        final JPAQuery<KE> countQuery = createQuery(predicate);

        JPAQuery<KE> query = (JPAQuery<KE>) querydsl.applyPagination(pageable, countQuery);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }

    @Override
    public long count(Predicate predicate) {
        return createQuery(predicate).fetchCount();
    }

    @Override
    public boolean exists(Predicate predicate) {
        return createQuery(predicate).fetchCount() > 0;
    }

    protected JPAQuery<KE> createQuery(Predicate... predicate) {
        JPAQuery<KE>       query    = getJpaQuery().where(predicate);
        CrudMethodMetadata metadata = getRepositoryMethodMetadata();
        if (metadata == null) {
            return query;
        }
        LockModeType type = metadata.getLockModeType();
        return type == null ? query : query.setLockMode(type);
    }

    private List<KE> executeSorted(JPQLQuery<KE> query, OrderSpecifier<?>... orders) {
        return executeSorted(query, new QSort(orders));
    }

    private List<KE> executeSorted(JPQLQuery<KE> query, Sort sort) {
        return querydsl.applySorting(sort, query).fetch();
    }
}
