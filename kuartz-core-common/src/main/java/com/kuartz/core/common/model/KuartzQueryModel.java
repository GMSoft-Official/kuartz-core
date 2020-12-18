package com.kuartz.core.common.model;

import com.kuartz.core.common.domain.KzPageable;
import com.kuartz.core.common.exception.StackedExceptionMessage;

import java.io.Serializable;

public class KuartzQueryModel implements Serializable {
    private static final long serialVersionUID = 6803637047885938752L;

    protected KzPageable pageable;

    public KuartzQueryModel() {
        //    bos yapici
    }

    public KuartzQueryModel(KzPageable pageable) {
        this.pageable = pageable;
    }

    public KzPageable getPageable() {
        return pageable;
    }

    public void setPageable(KzPageable pageable) {
        this.pageable = pageable;
    }
}
