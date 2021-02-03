package com.kuartz.core.common.model;

import com.kuartz.core.common.domain.KzPageable;

import java.io.Serializable;

public class KuartzQueryModel implements Serializable {
    private static final long serialVersionUID = 6803637047885938752L;


    protected Boolean deleted = Boolean.FALSE;
    protected KzPageable pageable = new KzPageable(0, 10);

    public KuartzQueryModel() {
        //    bos yapici
    }

    public KuartzQueryModel(Boolean deleted) {
        this.deleted = deleted;
    }

    public KuartzQueryModel(KzPageable pageable) {
        this.pageable = pageable;
    }

    public KuartzQueryModel(Boolean deleted, KzPageable pageable) {
        this.deleted  = deleted;
        this.pageable = pageable;
    }

    public KzPageable getPageable() {
        return pageable;
    }

    public void setPageable(KzPageable pageable) {
        this.pageable = pageable;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
