package com.kuartz.core.data.jpa.repository;

import com.kuartz.core.common.domain.KzPage;
import com.kuartz.core.common.domain.KzPageable;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.util.PageableResult;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Kutay Celebi
 * @since 24.09.2019
 */
@NoRepositoryBean
public interface KuartzRepository<KE extends KuartzEntity> extends Repository<KE, Long>, QuerydslPredicateExecutor<KE> {
    <T> JPAQuery<T> getJpaQuery();

    EntityManager getEntityManager();

    KE saveFlush(KE entity);

    List<KE> saveAllFlush(Iterable<KE> entities);

    KE update(KE entity);

    KE updateFlush(KE entity);

    void hardDelete(Long id);

    @Transactional
    void hardDelete(Iterable<Long> ids);

    Optional<KE> findOne(Predicate predicate);

    List<KE> findAll(Predicate predicate);

    List<KE> findAll(Predicate predicate, Sort sort);

    List<KE> findAll(Predicate predicate, OrderSpecifier<?>... orders);

    List<KE> findAll(OrderSpecifier<?>... orders);

    KzPage<KE> findAll(Predicate predicate, Pageable pageable);

    <T> KzPage<T>  applyPagination(KzPageable pageable, JPAQuery<T> query);

    @Override
    long count(Predicate predicate);

    @Override
    boolean exists(Predicate predicate);

    Optional<KE> findById(Long id);

    List<KE> findAll();

    void deleteAllByIds(Long... ids);

    void deleteAllByIdList(Iterable<Long> ids);

    void deleteById(Long id);

    //KzPage<KE> applyPagination(KzPageable pageable, JPAQuery<Object> query);

    @Nullable
    Querydsl getQuerydsl();

    Querydsl getRequiredQuerydsl();
}