package com.kuartz.core.data.jpa.entity;

import com.kuartz.core.common.util.DateUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author Kutay Celebi
 * @since 23.02.2019
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class KuartzEntity implements Serializable {

    public static final String DELETED_FIELD = "isDeleted";
    public static final String DELETED_AT_FIELD = "deletedAt";

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETED_AT")
    private Date deletedAt;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "DELETED")
    //@Type(type = "org.hibernate.type.NumericBooleanType") todo dialect ile cozelim. simdilik boyle kalsin.
    private Boolean isDeleted;

    @PrePersist
    void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }

    @PreRemove
    void preRemove() {
        this.isDeleted = Boolean.TRUE;
        this.deletedAt = DateUtils.suankiTarih();
    }

    public KuartzEntity() {
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
