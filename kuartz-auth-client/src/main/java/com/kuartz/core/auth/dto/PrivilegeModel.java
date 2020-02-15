package com.kuartz.core.auth.dto;

import com.kuartz.core.common.model.KuartzModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeModel extends KuartzModel {

    private String code;

    private PrivilegeModel parentPrivilege;

    public String getAuthority() {
        return this.code;
    }

}
