package com.kuartz.core.feign.client;

import com.kuartz.core.common.model.KuartzModel;
import com.kuartz.core.common.model.KuartzResponse;
import com.kuartz.core.common.model.KzMessageModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author Kutay Çelebi
 * @since 28.06.2020
 */
public interface KuartzCrudFeignClient<M extends KuartzModel> {

    @RequestMapping(value = "/save",
                    method = RequestMethod.POST,
                    consumes = {MediaType.APPLICATION_JSON_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    KuartzResponse<M> save(@Valid M model);

    @RequestMapping(value = "/delete/{id}",
                    method = RequestMethod.DELETE,
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    KuartzResponse<KzMessageModel> deleteById(@NotNull @Positive @PathVariable("id") Long id);

    @RequestMapping(value = "/get/{id}",
                    method = RequestMethod.GET,
                    produces = {MediaType.APPLICATION_JSON_VALUE})
    KuartzResponse<M> get(@NotNull @Positive @PathVariable("id") Long id);
}
