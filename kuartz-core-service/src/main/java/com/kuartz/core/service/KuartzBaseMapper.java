package com.kuartz.core.service;

import com.kuartz.core.common.model.KuartzModel;
import com.kuartz.core.data.jpa.entity.KuartzEntity;

/**
 * @author Kutay Celebi
 * @since 18.12.2020 23:01
 */
public interface KuartzBaseMapper<E, M> {

    E toEntity(M model);

    M toModel(E entity);
}
