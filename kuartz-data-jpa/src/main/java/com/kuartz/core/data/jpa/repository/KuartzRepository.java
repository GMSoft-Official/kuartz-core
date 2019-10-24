package com.kuartz.core.data.jpa.repository;

import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Kutay Celebi
 * @since 24.09.2019
 */
@NoRepositoryBean
public interface KuartzRepository<KE extends KuartzEntity> extends CrudRepository<KE, Long>, QuerydslPredicateExecutor<KE> {
    JPAQuery<KE> getJpaQuery();
}