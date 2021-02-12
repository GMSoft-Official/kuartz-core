package com.kuartz.core.data.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * @author Kutay Celebi
 * @since 11.02.2021 01:20
 */
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class KuartzReferenceEntity extends KuartzEntity {
    @Column(name = "PARENT_ID")
    private Long parentId;
    @Column(name = "REFERENCE_VERSION")
    private Long referenceVersion;


}
