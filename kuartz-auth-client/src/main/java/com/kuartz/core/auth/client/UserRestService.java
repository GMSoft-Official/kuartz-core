package com.kuartz.core.auth.client;

import com.kuartz.core.rest.model.KuartzResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "userRestService",
        path = "${kuartz.client.authzClient.userService.path}",
        url = "${kuartz.client.authzClient.url}")
public interface UserRestService {

    @RequestMapping(value = "/me",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    KuartzResponse<String> me(HttpServletRequest request);
}
