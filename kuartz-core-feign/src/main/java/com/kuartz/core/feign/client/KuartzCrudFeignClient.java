package com.kuartz.core.feign.client;

import com.kuartz.core.common.model.KuartzModel;
import com.kuartz.core.common.model.KuartzResponse;
import com.kuartz.core.common.model.KzMessageModel;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Kutay Çelebi
 * @since 28.06.2020
 */
@Validated
public interface KuartzCrudFeignClient<M extends KuartzModel> {

    @PostMapping(value = "/kaydet", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    KuartzResponse<M> kaydet(@Valid M model);

    @DeleteMapping(value = "/sil/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    KuartzResponse<KzMessageModel> sil(@NotNull @Positive @PathVariable("id") Long id);

    @GetMapping(value = "/getir/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    KuartzResponse<M> getir(@NotNull @Positive @PathVariable("id") Long id);
}
