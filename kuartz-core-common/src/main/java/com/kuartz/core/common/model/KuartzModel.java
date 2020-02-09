package com.kuartz.core.common.model;

import com.kuartz.core.common.util.DateUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class KuartzModel implements Serializable {

    private static final long serialVersionUID = -8331727143494828302L;

    public static final String DELETED_FIELD    = "isDeleted";
    public static final String DELETED_AT_FIELD = "deletedAt";

    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;

    private String uuid;

    private Boolean isDeleted;

    void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }

    void preRemove() {
        this.isDeleted = Boolean.TRUE;
        this.deletedAt = DateUtils.suankiTarih();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
