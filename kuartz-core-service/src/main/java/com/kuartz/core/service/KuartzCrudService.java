package com.kuartz.core.service;

import com.kuartz.core.common.model.KuartzModel;
import com.kuartz.core.common.model.KzMessageModel;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Kutay Çelebi
 * @since 28.06.2020
 */
@Validated
public interface KuartzCrudService<M extends KuartzModel> {

     M save (@Valid M model);

    KzMessageModel deleteById(@NotNull @Positive Long id);

    M get(@NotNull @Positive Long id);
}
