package com.kuartz.core.data.jpa.entity;

import com.kuartz.core.common.util.KzDateUtil;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Data
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class KuartzEntity implements Serializable {

    public static final  String ID_FIELD         = "ID";
    public static final  String DELETED_FIELD    = "deleted";
    public static final  String CREATED_FIELD    = "createdAt";
    public static final  String DELETED_AT_FIELD = "deletedAt";
    private static final long   serialVersionUID = 402199730764879680L;

    public KuartzEntity() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "version")
    @Version
    private Long version;

    @Column(name = "DELETED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(name = "UUID", unique = true)
    private String uuid;

    @Column(name = "DELETED")
    @NotNull
    //@Type(type = "org.hibernate.type.NumericBooleanType") todo dialect ile cozelim. simdilik boyle kalsin.
    private Boolean deleted = false;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KuartzEntity that = (KuartzEntity) o;
        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @PrePersist
    void prePersist() {
        this.uuid = UUID.randomUUID().toString();
    }

    @PreRemove
    void preRemove() {
        this.deleted   = Boolean.TRUE;
        this.deletedAt = KzDateUtil.now();
    }
}
