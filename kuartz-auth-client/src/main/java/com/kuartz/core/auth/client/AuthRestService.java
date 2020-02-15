package com.kuartz.core.auth.client;

import com.kuartz.core.auth.dto.LoginModel;
import com.kuartz.core.auth.dto.RegisterModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "clientRestService",
             url = "${kuartz.client.authzClient.url}", path = "${kuartz.client.authzClient.authService.path}")
@Validated
public interface AuthRestService {

    @RequestMapping(value = "/login", method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    void login(@Valid LoginModel loginModel);

    @RequestMapping(value = "/register", method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE, produces = {MediaType.APPLICATION_JSON_VALUE})
    void register(@Valid RegisterModel registerModel);

}
