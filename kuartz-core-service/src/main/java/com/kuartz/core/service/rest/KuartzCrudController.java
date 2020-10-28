package com.kuartz.core.service.rest;

import com.kuartz.core.common.model.KuartzModel;
import com.kuartz.core.common.model.KuartzResponse;
import com.kuartz.core.common.model.KzMessageModel;
import com.kuartz.core.feign.client.KuartzCrudFeignClient;
import com.kuartz.core.service.KuartzCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Kutay Çelebi
 * @since 28.06.2020
 */
public class KuartzCrudController<M extends KuartzModel, S extends KuartzCrudService<M>> extends AbstractKuartzController
        implements KuartzCrudFeignClient<M> {

    @Autowired
    protected S service;

    @Override
    public KuartzResponse<M> save(@RequestBody M model) {
        return new KuartzResponse<>(service.save(model));
    }

    @Override
    public KuartzResponse<KzMessageModel> deleteById(@PathVariable("id") Long id) {
        return new KuartzResponse<>(service.deleteById(id));
    }

    @Override
    public KuartzResponse<M> get(@PathVariable("id") Long id) {
        return new KuartzResponse<>(service.get(id));
    }
}