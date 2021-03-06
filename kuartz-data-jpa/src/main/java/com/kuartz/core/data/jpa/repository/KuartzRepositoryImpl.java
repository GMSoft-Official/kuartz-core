package com.kuartz.core.data.jpa.repository;

import com.kuartz.core.common.domain.KzPage;
import com.kuartz.core.common.domain.KzPageable;
import com.kuartz.core.common.util.KzDateUtil;
import com.kuartz.core.data.jpa.bean.KuartzEntityPathResolver;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.util.ExecutionUtils;
import com.querydsl.core.DefaultQueryMetadata;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QSort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Kutay Celebi
 * @since 24.09.2019
 */
@Transactional
public class KuartzRepositoryImpl<KE extends KuartzEntity> extends SimpleJpaRepository<KE, String> implements KuartzRepository<KE>,
        QuerydslPredicateExecutor<KE> {

    private static final KuartzEntityPathResolver PATH_RESOLVER = KuartzEntityPathResolver.INSTANCE;

    protected JpaEntityInformation<KE, ?> entityInformation;
    protected final EntityManager em;
    private final EntityPath<KE> path;
    private final PathBuilder<KE> builder;
    private final Querydsl querydsl;

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

    public <T> JPAQuery<T> getJpaQuery() {
        return new JPAQuery<>(em);
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

    @Override
    public KE saveFlush(KE entity) {
        KE result;
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            result =entity;
        } else {
            result= em.merge(entity);
        }
        flush();
        return result;
    }

    @Override
    public KE update(KE entity) {
        return this.save(entity);
    }

    @Override
    public KE updateFlush(KE entity) {
        return this.saveFlush(entity);
    }

    @Override
    public <S extends KE> List<S> saveAll(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        List<S> result = new ArrayList<S>();
        for (S entity : entities) {
            result.add(this.save(entity));
        }
        return result;
    }

    @Override
    public List<KE> saveAllFlush(Iterable<KE> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        List<KE> result = new ArrayList<KE>();
        for (KE entity : entities) {
            result.add(saveFlush(entity));
        }
        return result;
    }

    @Override
    public void hardDelete(String id) {
        Assert.notNull(id, "ID null olamaz.");
        final KE entity = findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1));

        Assert.notNull(entity, "The entity must not be null!");
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public void hardDelete(Iterable<String> ids) {
        Assert.notNull(ids, "ID null olamaz.");
        for (String id : ids) {
            hardDelete(id);
        }
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
    public List<KE> findAll() {
        return executeSorted(createQuery(), Sort.by(Sort.Direction.DESC, KuartzEntity.CREATED_FIELD));
    }

    @Override
    public List<KE> findAll(Predicate predicate) {
        return createQuery(predicate).fetch();
    }

    @Override
    public List<KE> findAll(Predicate predicate, Sort sort) {
        JPAQuery<KE> query = createQuery(predicate);
        return executeSorted(query, sort);
    }

    @Override
    public List<KE> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        JPAQuery<KE> query = createQuery(predicate);
        return executeSorted(query, orders);
    }

    @Override
    public List<KE> findAll(OrderSpecifier<?>... orders) {
        Assert.notNull(orders, "Order specifiers must not be null!");
        return executeSorted(createQuery(new Predicate[0]).select(path), orders);
    }

    @Override
    public KzPage<KE> findAll(Predicate predicate, Pageable pageable) {

        Assert.notNull(pageable, "Pageable must not be null!");

        final JPAQuery<KE> countQuery = createQuery(predicate);

        JPAQuery<KE> query = (JPAQuery<KE>) querydsl.applyPagination(pageable, countQuery);
        if (pageable.getSort().isUnsorted()) {
            return ExecutionUtils.getPage(executeSorted(query, Sort.by(Sort.Direction.DESC, KuartzEntity.CREATED_FIELD)), pageable,
                                          countQuery::fetchCount);
        }
        return ExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }

    @Override
    public List<KE> findAllById(Iterable<String> longs) {
        return super.findAllById(longs);
    }


    @Override
    public <T> KzPage<T> applyPagination(KzPageable pageable, JPAQuery<T> query) {
        final JPQLQuery<T> applyPagination = querydsl.applyPagination(pageable, query);
        return ExecutionUtils.getPage(applyPagination.fetch(), pageable, query::fetchCount);
    }

    @Override
    public long count(Predicate predicate) {
        return createQuery(predicate).fetchCount();
    }

    @Override
    public boolean exists(Predicate predicate) {
        return createQuery(predicate).fetchCount() > 0;
    }

    @Override
    public void delete(KE entity) {
        boolean isExists = existsById(entity.getId());
        Assert.isTrue(isExists, "ENTITY VERITABANINDA YOK");
        entity.setDeleted(Boolean.TRUE);
        entity.setDeletedAt(KzDateUtil.now());
        this.updateFlush(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends KE> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        findAll().forEach(this::delete);
    }

    @Override
    public void deleteAllByIds(String... ids) {
        Assert.notNull(ids, "SILINECEK ENTITY ID BOS OLAMAZ"); // todo bu hatalari mesaja cekelim
        for (String id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAllByIdList(Iterable<String> ids) {
        Assert.notNull(ids, "SILINECEK ENTITY ID BOS OLAMAZ");
        for (String id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void deleteById(String id) {
        Assert.notNull(id, "SILINECEK ENTITY ID BOS OLAMAZ"); // todo bu hatalari mesaja cekelim
        Optional<KE> optional = findById(id);
        if (optional.isPresent()) {
            KE entity = optional.get();
            entity.setDeleted(Boolean.TRUE);
            entity.setDeletedAt(KzDateUtil.now());
            delete(entity);
        } else {
            throw new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id),
                                                     1);
        }
    }

    protected JPAQuery<KE> createQuery(Predicate... predicate) {

        DefaultQueryMetadata defaultQueryMetadata = new DefaultQueryMetadata();
        //OrderSpecifier<Date> order = new OrderSpecifier<>(Order.DESC, builder.getDate(KuartzEntity.CREATED_FIELD, Date.class),
        //                                                  OrderSpecifier.NullHandling.NullsLast);
        //defaultQueryMetadata.addOrderBy(order);
        defaultQueryMetadata.addWhere(
                builder.getBoolean(KuartzEntity.DELETED_FIELD).isNull().or(builder.getBoolean(KuartzEntity.DELETED_FIELD).isFalse()));
        JPAQuery<KE> query = new JPAQuery<>(em, defaultQueryMetadata);
        query.from(this.path);
        query.where(predicate);

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
        Sort.by(Sort.Direction.DESC, KuartzEntity.CREATED_FIELD);
        return querydsl.applySorting(sort, query).fetch();
    }

    /**
     * Returns the underlying Querydsl helper instance.
     *
     * @return
     */
    @Nullable
    @Override
    public Querydsl getQuerydsl() {
        return this.querydsl;
    }

    @Override
    public Querydsl getRequiredQuerydsl() {

        if (querydsl == null) {
            throw new IllegalStateException("Querydsl is null!");
        }

        return querydsl;
    }


}
