package com.kuartz.core.data.jpa.bean;

import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.repository.KuartzRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;

/**
 * @author Kutay Celebi
 * @since 27.09.2019
 */
public class KuartzJpaRepositoryFactory<KE extends KuartzEntity> extends JpaRepositoryFactory {

    private final EntityPathResolver simpleEntityPathResolver = SimpleEntityPathResolver.INSTANCE;

    private final KuartzEntityPathResolver pathResolver = KuartzEntityPathResolver.INSTANCE;

    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public KuartzJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.setEntityPathResolver(pathResolver);
    }

    @Override
    protected JpaRepositoryImplementation<KE, Long> getTargetRepository(
            RepositoryInformation information, EntityManager entityManager) {

        return new KuartzRepositoryImpl<>(getEntityInformation((Class<KE>) information.getDomainType()),
                                            entityManager);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return KuartzRepositoryImpl.class;
    }
}
