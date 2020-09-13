package com.kuartz.core.data.jpa.repository;

import com.kuartz.core.common.domain.KzPage;
import com.kuartz.core.common.domain.KzPageable;
import com.kuartz.core.common.util.KzDateUtil;
import com.kuartz.core.data.jpa.bean.KuartzEntityPathResolver;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.util.KzPageableUtil;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QSort;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Kutay Celebi
 * @since 24.09.2019
 */
@Transactional
public class KuartzRepositoryImpl<KE extends KuartzEntity> extends SimpleJpaRepository<KE, Long> implements
                                                                                                 KuartzRepository<KE>,
                                                                                                 QuerydslPredicateExecutor<KE> {

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
    @Transactional
    public <S extends KE> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Transactional
    public KE saveFlush(KE entity) {
        KE result = save(entity);
        flush();
        return result;
    }

    @Transactional
    public KE update(KE entity) {
        return save(entity);
    }

    @Transactional
    public KE updateFlush(KE entity) {
        return saveFlush(entity);
    }

    @Override
    @Transactional
    public <S extends KE> List<S> saveAll(Iterable<S> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        List<S> result = new ArrayList<S>();
        for (S entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    @Transactional
    public List<KE> saveAllFlush(Iterable<KE> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        List<KE> result = new ArrayList<KE>();
        for (KE entity : entities) {
            result.add(saveFlush(entity));
        }
        return result;
    }

    @Override
    @Transactional
    public void hardDelete(Long id) {
        Assert.notNull(id, "ID null olamaz.");
        super.deleteById(id);
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
        return createQuery().fetch();
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
    public Page<KE> findAll(Predicate predicate, Pageable pageable) {

        Assert.notNull(pageable, "Pageable must not be null!");

        final JPAQuery<KE> countQuery = createQuery(predicate);

        JPAQuery<KE> query = (JPAQuery<KE>) querydsl.applyPagination(pageable, countQuery);

        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }

    @Override
    public KzPage<KE> findAll(Predicate predicate, KzPageable pageable) {
        Assert.notNull(pageable, "Pageable must not be null!");

        final JPAQuery<KE> query = createQuery(predicate);

        PageRequest toPageable = KzPageableUtil.kzPageableToPageable(pageable);
        JPAQuery<KE> applyPagination = (JPAQuery<KE>) querydsl.applyPagination(toPageable, query);
        Page<KE> page = PageableExecutionUtils.getPage(applyPagination.fetch(), toPageable, query::fetchCount);
        return KzPageableUtil.pageToKzPage(page);
    }

    @Override
    public KzPage<KE> applyPagination(KzPageable pageable, JPAQuery<KE> query) {
        final PageRequest toPageable = KzPageableUtil.kzPageableToPageable(pageable);
        final JPQLQuery<KE> applyPagination = querydsl.applyPagination(toPageable, query);
        final Page<KE> page = PageableExecutionUtils.getPage(applyPagination.fetch(), toPageable, query::fetchCount);
        return KzPageableUtil.pageToKzPage(page);
    }

    @Override
    public KzPage<KE> applyPagination(Pageable pageable, JPAQuery<KE> query) {
        final JPQLQuery<KE> applyPagination = querydsl.applyPagination(pageable, query);
        final Page<KE> page = PageableExecutionUtils.getPage(applyPagination.fetch(), pageable, query::fetchCount);
        return KzPageableUtil.pageToKzPage(page);
    }

    @Override
    public long count(Predicate predicate) {
        return createQuery(predicate).fetchCount();
    }

    @Override
    public boolean exists(Predicate predicate) {
        return createQuery(predicate).fetchCount() > 0;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "SILINECEK ENTITY ID BOS OLAMAZ"); // todo bu hatalari mesaja cekelim
        Optional<KE> optional = findById(id);
        if (optional.isPresent()) {
            KE entity = optional.get();
            entity.setDeleted(Boolean.TRUE);
            entity.setDeletedAt(KzDateUtil.suankiTarih());
            updateFlush(entity);
        } else {
            throw new EmptyResultDataAccessException(
                    String.format("No %s entity with id %s exists!", entityInformation.getJavaType(), id), 1);
        }
    }

    @Override
    @Transactional
    public void delete(KE entity) {
        boolean isExists = existsById(entity.getId());
        Assert.isTrue(isExists, "ENTITY VERITABANINDA YOK");

        entity.setDeleted(Boolean.TRUE);
        entity.setDeletedAt(KzDateUtil.suankiTarih());
        updateFlush(entity);
    }

    @Override
    @Transactional
    public void deleteAll(Iterable<? extends KE> entities) {
        entities.forEach(this::delete);
    }

    @Override
    @Transactional
    public void deleteAll() {
        findAll().forEach(this::delete);
    }


    protected JPAQuery<KE> createQuery(Predicate... predicate) {
        JPAQuery<KE> query = getJpaQuery().where(predicate);
        query.where(builder.getBoolean(KuartzEntity.DELETED_FIELD).isNull().or(builder.getBoolean(KuartzEntity.DELETED_FIELD).isFalse()));

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

    @Override
    public List<KE> findAllById(Iterable<Long> longs) {
        return super.findAllById(longs);
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
