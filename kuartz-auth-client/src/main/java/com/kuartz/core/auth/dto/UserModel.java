package com.kuartz.core.auth.dto;

import com.kuartz.core.common.model.KuartzModel;
import com.kuartz.core.common.util.KzUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserModel extends KuartzModel {


    private String username;

    private String password;

    @Email
    private String email;

    private String name;

    private String surname;

    private List<RoleModel> roleList;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Set<PrivilegeModel> getAuthorities() {
        Set<PrivilegeModel> authorities = new HashSet<>();
        if (!KzUtil.isEmpty(roleList)) {
            roleList.stream().map(r -> r.getPrivilegeList()).forEach(authorities::addAll);
        }
        return authorities;
    }
}
