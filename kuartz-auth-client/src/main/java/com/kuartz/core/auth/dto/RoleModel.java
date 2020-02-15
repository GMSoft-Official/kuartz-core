package com.kuartz.core.auth.dto;

import com.kuartz.core.common.model.KuartzModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleModel extends KuartzModel {
    private String code;

    private String name;

    private String description;

    private List<PrivilegeModel> privilegeList;

    public RoleModel(String name) {
        this.name = name;
    }
}
