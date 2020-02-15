package com.kuartz.core.auth.dto;

import com.kuartz.core.common.model.KuartzModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class KuartzClientDetailModel extends KuartzModel {
    private String  clientId;
    private String  resourceIds;
    private String  clientSecret;
    private String  scope;
    private String  authorizedGrantTypes;
    private String  registeredRedirectUri;
    private String  authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private String  additionalInformation;
    private String  autoApproveScopes;

}
