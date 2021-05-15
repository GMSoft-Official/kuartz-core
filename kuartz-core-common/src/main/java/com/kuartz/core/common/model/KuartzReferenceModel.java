package com.kuartz.core.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Kutay Celebi
 * @since 12.02.2021 14:37
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class KuartzReferenceModel extends KuartzModel {
    private static final long serialVersionUID = -6646252747310093755L;
    private Long parentId;
    private Long referenceVersion;
}
