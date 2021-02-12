package com.kuartz.core.common.model;

/**
 * @author Kutay Celebi
 * @since 12.02.2021 14:37
 */
public class KuartzReferenceModel extends KuartzModel {
    private Long parentId;
    private Long referenceVersion;

    public KuartzReferenceModel() {
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getReferenceVersion() {
        return referenceVersion;
    }

    public void setReferenceVersion(Long referenceVersion) {
        this.referenceVersion = referenceVersion;
    }
}
