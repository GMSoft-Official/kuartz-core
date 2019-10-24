package com.kuartz.core.data.jpa.repository;

import com.kuartz.core.data.jpa.bean.KuartzEntityPathResolver;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * @author Kutay Celebi
 * @since 24.09.2019
 */
@NoRepositoryBean
public class KuartzRepositoryImpl<KE extends KuartzEntity> extends SimpleJpaRepository<KE, Long> implements KuartzRepository<KE> {

    private static final KuartzEntityPathResolver PATH_RESOLVER = KuartzEntityPathResolver.INSTANCE;

    private JpaEntityInformation<KE, ?> entityInformation;
    private final EntityManager entityManager;
    private final EntityPath<KE> path;
    private final PathBuilder<KE> builder;
    private final Querydsl querydsl;

    public KuartzRepositoryImpl(
            JpaEntityInformation<KE, ?> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
            this.entityInformation = entityInformation;
            this.path = PATH_RESOLVER.createPath(entityInformation.getJavaType());
            this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
            this.querydsl = new Querydsl(entityManager, builder);
            this.entityManager = entityManager;
    }

    public JPAQuery<KE> getJpaQuery() {
        return new JPAQuery<>(entityManager);
    }

    @Transactional
    @Override
    public List<KE> findAll() {
        return super.findAll();
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {
    }


    @Override
    public void delete(KE entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends KE> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends KE> S save(S entity) {
        return null;
    }

    @Override
    public <S extends KE> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<KE> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }


    @Override
    public Optional<KE> findOne(Predicate predicate) {
        return Optional.empty();
    }

    @Override
    public Iterable<KE> findAll(Predicate predicate) {
        return null;
    }

    @Override
    public Iterable<KE> findAll(Predicate predicate, Sort sort) {
        return null;
    }

    @Override
    public Iterable<KE> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return null;
    }

    @Override
    public Iterable<KE> findAll(OrderSpecifier<?>... orders) {
        return null;
    }

    @Override
    public Page<KE> findAll(Predicate predicate, Pageable pageable) {
        return null;
    }

    @Override
    public long count(Predicate predicate) {
        return 0;
    }

    @Override
    public boolean exists(Predicate predicate) {
        return false;
    }
}
