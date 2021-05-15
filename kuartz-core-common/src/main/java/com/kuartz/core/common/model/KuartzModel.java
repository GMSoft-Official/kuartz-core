package com.kuartz.core.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
public class KuartzModel implements Serializable {

    private static final long serialVersionUID = - 8331727143494828302L;

    public static final String DELETED_FIELD = "isDeleted";
    public static final String DELETED_AT_FIELD = "deletedAt";

    private String id;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String lastModifiedBy;

    private Date deletedAt;

    private Long version;

    private String uuid;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    public KuartzModel() {
    }
}
