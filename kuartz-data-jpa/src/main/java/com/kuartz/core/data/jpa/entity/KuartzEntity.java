package com.kuartz.core.data.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.kuartz.core.common.util.KzDateUtil;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Kutay Celebi
 * @since 23.02.2019
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "uuid")
@DynamicUpdate
@DynamicInsert
public class KuartzEntity implements Serializable {

    public static final  String ID_FIELD         = "ID";
    public static final  String DELETED_FIELD    = "isDeleted";
    public static final  String CREATED_FIELD    = "createdAt";
    public static final  String DELETED_AT_FIELD = "deletedAt";
    private static final long   serialVersionUID = 402199730764879680L;

    @Id
    @GenericGenerator(name = "sequence-generator",
                      strategy = "com.kuartz.core.data.jpa.KuartzSequenceGenerator",
                      parameters = {
                              @Parameter(name = "initial_value", value = "1"),
                              @Parameter(name = "increment_size", value = "10"),
                              @Parameter(name = "prefer_sequence_per_entity", value = "true")
                      }
    )
    @GeneratedValue(generator = "sequence-generator")
    @Column(name = "ID")
    @org.springframework.data.annotation.AccessType(org.springframework.data.annotation.AccessType.Type.PROPERTY)
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "version")
    @Version
    private Long version;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETED_AT")
    private Date deletedAt;

    @Column(name = "UUID", unique = true)
    private String uuid;

    @Column(name = "DELETED")
    @NotNull
    //@Type(type = "org.hibernate.type.NumericBooleanType") todo dialect ile cozelim. simdilik boyle kalsin.
    private Boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KuartzEntity that = (KuartzEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @PrePersist
    void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }

    @PreRemove
    void preRemove() {
        this.isDeleted = Boolean.TRUE;
        this.deletedAt = KzDateUtil.suankiTarih();
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
