package com.kuartz.core.data.jpa.bean;

import com.kuartz.core.data.jpa.entity.KuartzEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;

/**
 * @author Kutay Celebi
 * @since 24.09.2019
 */
public class KuartzRepositoryFactoryBean<R extends JpaRepository<KE, I>, KE extends KuartzEntity,
        I extends Long> extends JpaRepositoryFactoryBean<R, KE, I> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public KuartzRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new KuartzJpaRepositoryFactory(entityManager);
    }


}
