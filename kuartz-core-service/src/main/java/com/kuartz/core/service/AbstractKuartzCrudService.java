package com.kuartz.core.service;

import com.kuartz.core.common.model.KuartzModel;
import com.kuartz.core.common.model.KzMessageModel;
import com.kuartz.core.data.jpa.entity.KuartzEntity;
import com.kuartz.core.data.jpa.repository.KuartzRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @param <KR> - Autowire edilecek Repository sınıftır.
 * @param <KE> -
 * @param <M>
 * @author Kutay Celebi
 * @since 18.12.2020 22:42
 */
public abstract class AbstractKuartzCrudService<KE extends KuartzEntity, KM extends KuartzModel, KR extends KuartzRepository<KE>, M extends KuartzBaseMapper<KE,KM>> extends
        AbstractKuartzService implements KuartzCrudService<KM> {

    @Autowired
    private KR repository;
    @Autowired
    private M mapper;

    //protected abstract KE toEntity(KM model);
    //
    //protected abstract KM toModel(KE entity);

    @Override
    public KM kaydet(KM model) {
        final KE saved = repository.saveFlush(mapper.toEntity(model));
        return mapper.toModel(saved);
    }

    @Override
    public KzMessageModel sil(Long id) {
        repository.deleteById(id);
        return KzMessageModel.succeed().addMessage(KuartzServiceMessageSourceAccessor.getAccessor().getMessage("service.transaction.sil"));
    }

    @Override
    public KM getir(Long id) {
        return mapper.toModel(repository.findById(id).orElse(null));
    }
}
