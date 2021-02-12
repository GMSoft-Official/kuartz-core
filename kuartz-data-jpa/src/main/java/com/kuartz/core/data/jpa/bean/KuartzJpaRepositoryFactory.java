package com.kuartz.core.data.jpa.bean;

import com.kuartz.core.data.jpa.entity.KuartzReferenceEntity;
import com.kuartz.core.data.jpa.repository.KuartzReferenceRepository;
import com.kuartz.core.data.jpa.repository.KuartzRepository;
import com.kuartz.core.data.jpa.repository.KuartzRepositoryImpl;
import com.kuartz.core.data.jpa.repository.KuartzReferenceRepositoryImpl;
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
public class KuartzJpaRepositoryFactory<KE extends KuartzReferenceEntity> extends JpaRepositoryFactory {

    public static final Logger LOGGER = LoggerFactory.getLogger(KuartzJpaRepositoryFactory.class);
    private final KuartzEntityPathResolver pathResolver = KuartzEntityPathResolver.INSTANCE;
    private final EntityManager em;

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
        final boolean isReferenceRepository = KuartzReferenceRepository.class.isAssignableFrom(metadata.getRepositoryInterface());
        if (isKuartzRepo) {
            JpaEntityInformation<KE, Object> entityInformation = getEntityInformation((Class<KE>) metadata.getDomainType());
            KuartzRepositoryImpl<?> kuartzRepository = new KuartzRepositoryImpl<>(entityInformation, em);
            // fixme 4-5 kere giriyor bakalim buna.
            fragments.append(RepositoryFragment.implemented(kuartzRepository));
        } else if (isReferenceRepository) {
            JpaEntityInformation<KE, Object> entityInformation = getEntityInformation((Class<KE>) metadata.getDomainType());
            KuartzReferenceRepositoryImpl<?> kuartzRepository = new KuartzReferenceRepositoryImpl<>(entityInformation, em);
            // fixme 4-5 kere giriyor bakalim buna.
            fragments.append(RepositoryFragment.implemented(kuartzRepository));
        } else {
            throw new UnsupportedOperationException("Kuartz Repository interface bulunamadi");
        }
        return fragments;
    }

    @Override
    protected JpaRepositoryImplementation<KE, Long> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
        final JpaEntityInformation<KE, Object> entityInformation = getEntityInformation((Class<KE>) information.getDomainType());
        final boolean isKuartzRepo = KuartzRepositoryImpl.class.isAssignableFrom(information.getRepositoryBaseClass());
        final boolean isReferenceRepo = KuartzReferenceRepositoryImpl.class.isAssignableFrom(information.getRepositoryBaseClass());
        if (isReferenceRepo) {
            return new KuartzReferenceRepositoryImpl<>(entityInformation, entityManager);
        } else if (isKuartzRepo) {
            return new KuartzRepositoryImpl<>(entityInformation, entityManager);
        } else {
            throw new UnsupportedOperationException("Kuartz Repository interface bulunamadi");
        }
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        final boolean isKuartzRepo = KuartzRepository.class.isAssignableFrom(metadata.getRepositoryInterface());
        final boolean isReferenceRepo = KuartzReferenceRepository.class.isAssignableFrom(metadata.getRepositoryInterface());
        if (isReferenceRepo) {
            return KuartzReferenceRepositoryImpl.class;
        } else if (isKuartzRepo) {
            return KuartzRepositoryImpl.class;
        } else {
            throw new UnsupportedOperationException("Kuartz Repository interface bulunamadi");
        }
    }
}
