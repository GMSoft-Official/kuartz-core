package com.kuartz.core.auth.dto;

import com.kuartz.core.common.model.KuartzModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceModel extends KuartzModel {

    private String resourceName;

}
