package com.kuartz.core.data.jpa.repository;

import com.kuartz.core.common.domain.KzPage;
import com.kuartz.core.common.domain.KzPageable;
import com.kuartz.core.common.util.KzDateUtil;
import com.kuartz.core.data.jpa.bean.KuartzEntityPathResolver;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.entity.KuartzReferenceEntity;
import com.kuartz.core.data.jpa.util.ExecutionUtils;
import com.querydsl.core.DefaultQueryMetadata;
import com.querydsl.core.NonUniqueResultException;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.SerializationUtils;
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
import org.springframework.util.ReflectionUtils;

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
public class KuartzReferenceRepositoryImpl<KE extends KuartzReferenceEntity> extends KuartzRepositoryImpl<KE> implements KuartzReferenceRepository<KE>{

    public KuartzReferenceRepositoryImpl(JpaEntityInformation<KE, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
    }

    @Override
    public <S extends KE> S save(S entity) {
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
            return entity;
        } else {
            delete(entity);
            final S clone = SerializationUtils.clone(entity);
            clone.setVersion(0L);
            clone.setDeletedAt(null);
            clone.setDeleted(false);
            clone.setLastModifiedBy(null);
            clone.setUpdatedAt(null);
            clone.setCreatedAt(null);
            clone.setCreatedBy(null);
            clone.setUuid(null);
            if (clone.getReferenceVersion() != null) {
                clone.setReferenceVersion(clone.getReferenceVersion() + 1);
            }

            if (clone.getParentId() == null) {
                clone.setParentId(clone.getId());
            }
            clone.setId(null);

            return super.save(clone);
        }
    }

    @Override
    public KE saveFlush(KE entity) {
        final KE result = save(entity);
        flush();
        return result;
    }

    @Override
    public void delete(KE entity) {
        boolean isExists = existsById(entity.getId());
        Assert.isTrue(isExists, "ENTITY VERITABANINDA YOK");
        entity.setDeleted(Boolean.TRUE);
        entity.setDeletedAt(KzDateUtil.now());
        if (entityInformation.isNew(entity)) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        flush();
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
}
