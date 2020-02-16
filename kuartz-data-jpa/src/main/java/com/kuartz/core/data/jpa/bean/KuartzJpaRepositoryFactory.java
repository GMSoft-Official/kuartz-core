package com.kuartz.core.data.jpa.bean;

import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.repository.KuartzRepository;
import com.kuartz.core.data.jpa.repository.KuartzRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFragment;

import javax.persistence.EntityManager;

/**
 * @author Kutay Celebi
 * @since 27.09.2019
 */
public class KuartzJpaRepositoryFactory<KE extends KuartzEntity> extends JpaRepositoryFactory {

    public static final Logger                   LOGGER       = LoggerFactory.getLogger(KuartzJpaRepositoryFactory.class);
    private final       KuartzEntityPathResolver pathResolver = KuartzEntityPathResolver.INSTANCE;
    private final       EntityManager            em;

    /**
     * Creates a new {@link JpaRepositoryFactory}.
     *
     * @param entityManager must not be {@literal null}
     */
    public KuartzJpaRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.setEntityPathResolver(pathResolver);
        this.em = entityManager;
    }

    @Override
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {
        // Bos fragment olusturulur.
        RepositoryComposition.RepositoryFragments fragments = RepositoryComposition.RepositoryFragments.empty();

        // gelen repo meta bilgisi KuartzRepository interfacesinden mi turedigi kontrol edilir.
        boolean isKuartzRepo = KuartzRepository.class.isAssignableFrom(metadata.getRepositoryInterface());
        if (isKuartzRepo) {
            JpaEntityInformation<KE, Object> entityInformation = getEntityInformation((Class<KE>) metadata.getDomainType());
            KuartzRepositoryImpl<KE> kuartzRepository = new KuartzRepositoryImpl<>(entityInformation, em);
            // fixme 4-5 kere giriyor bakalim buna.
            fragments.append(RepositoryFragment.implemented(kuartzRepository));
        } else {
            throw new UnsupportedOperationException("Kuartz Repository interface bulunamadi");
        }
        return fragments;
    }

    @Override
    protected JpaRepositoryImplementation<KE, Long> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        return new KuartzRepositoryImpl<>(getEntityInformation((Class<KE>) information.getDomainType()), entityManager);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return KuartzRepositoryImpl.class;
    }
}
